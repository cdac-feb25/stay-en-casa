package com.stayen.casa.gatewayservice.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stayen.casa.gatewayservice.constant.Endpoints;
import com.stayen.casa.gatewayservice.constant.HeaderConstant;
import com.stayen.casa.gatewayservice.constant.TokenConstant;
import com.stayen.casa.gatewayservice.dto.ErrorResponseDTO;
import com.stayen.casa.gatewayservice.enums.TokenError;
import com.stayen.casa.gatewayservice.enums.TokenType;
import com.stayen.casa.gatewayservice.exception.TokenException;
import com.stayen.casa.gatewayservice.model.User;
import com.stayen.casa.gatewayservice.security.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class JwtFilter extends OncePerRequestFilter {
    private static final String CLASS_NAME = JwtFilter.class.getSimpleName();

    private final JwtUtils jwtUtils;
    private final ObjectMapper mapper;

    private static final List<String> PATH_EXCLUDED_FROM_JWT_FILTER = List.of(
            (Endpoints.Auth.BASE_URL + Endpoints.Auth.LOGIN),  // "/api/v1/auth/login"
            (Endpoints.Auth.BASE_URL + Endpoints.Auth.SIGNUP)  // "/api/v1/auth/signup"
//            (Endpoints.Token.BASE_URL + Endpoints.Token.REFRESH_TOKEN),  // "/api/v1/auth/token/refresh"
    );

    private static final List<String> ONLY_REFRESH_TOKEN_PATH = List.of(
            (Endpoints.Token.BASE_URL + Endpoints.Token.REFRESH_TOKEN)  // "/api/v1/auth/token/refresh"
    );

    @Autowired
    public JwtFilter(JwtUtils jwtUtils, ObjectMapper mapper) {
        this.jwtUtils = jwtUtils;
        this.mapper = mapper;
    }

    /**
     * Verify whether to apply JWT filter
     * on the request
     *
     * @param incomingPath
     * @return
     */
    private static boolean isPathExcludedFromJwtFilter(String incomingPath) {
        return PATH_EXCLUDED_FROM_JWT_FILTER
                .stream()
                .anyMatch((path) -> path.equalsIgnoreCase(incomingPath));
    }

    /**
     *
     *
     * @param incomingPath
     * @return
     */
    private static boolean isRefreshTokenPath(String incomingPath) {
        return ONLY_REFRESH_TOKEN_PATH
                .stream()
                .anyMatch((path) -> path.equalsIgnoreCase(incomingPath));
    }

    private void setAuthErrorResponse(HttpServletResponse response, TokenError tokenError) throws IOException {
        response.setContentType("application/json");

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(mapper.writeValueAsString(new ErrorResponseDTO(tokenError)));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(isPathExcludedFromJwtFilter(request.getServletPath())) {
            System.out.println(CLASS_NAME + " - Request Skipped from authentication.... " + request.getServletPath());
            printHeaderReceived(request);
            return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String incomingRequestPath = request.getServletPath();
        System.out.println(CLASS_NAME + " - Request Intercepted.... " + incomingRequestPath);

        printHeaderReceived(request);
        try {
            String token;
            if(isRefreshTokenPath(incomingRequestPath)) {
                token = extractTokenFromCookie(request);
            } else {
                token = extractTokenFromAuthHeader(request);
            }

            Claims jwtClaims = jwtUtils.validateToken(token);

            TokenType tokenType = TokenType.valueOf(jwtClaims.get(TokenConstant.TOKEN_TYPE, String.class));
            System.out.println(CLASS_NAME + " - Token Type : " + tokenType);

            /**
             * ACCESS token cannot be used to refresh tokens,
             * even if it is valid JWT
             */
            if(tokenType == TokenType.TEMP ||
                    tokenType == TokenType.ACCESS && isRefreshTokenPath(incomingRequestPath)) {
                System.out.println(CLASS_NAME + " - Invalid -- Using ACCESS token for refreshing");
                throw new TokenException(TokenError.INVALID);
            }


            /**
             * REFRESH token is only allowed for
             * api/v1/auth/token/refresh, or path specified in ONLY_REFRESH_TOKEN_PATH
             * And
             * All the other request path should not be allowed
             */
            if(tokenType == TokenType.REFRESH && !isRefreshTokenPath(incomingRequestPath)) {
                System.out.println(CLASS_NAME + " - INVALID -- Using REFRESH token for general purpose");
                throw new TokenException(TokenError.INVALID);
            }

            String uid = jwtClaims.getSubject();
            String email = jwtClaims.get(TokenConstant.EMAIL, String.class);
            String deviceId = jwtClaims.get(TokenConstant.DEVICE_ID, String.class);

            User user = new User(uid, email, deviceId, token);
            List<GrantedAuthority> authorities = List.of();

            UsernamePasswordAuthenticationToken verifiedUser = new UsernamePasswordAuthenticationToken(uid, null, authorities);
            verifiedUser.setDetails(user);

            SecurityContextHolder.getContext().setAuthentication(verifiedUser);

            /**
             * proceed with further filter chain
             */
            System.out.println(CLASS_NAME + " - JWT Verified / User saved....");

            filterChain.doFilter(request, response);
        }
        catch (TokenException e) {
            /**
             * Intercepting
             * EMPTY, INVALID, BLOCKED
             */
            setAuthErrorResponse(response, e.getTokenError());
        } catch (ExpiredJwtException e) {
            setAuthErrorResponse(response, TokenError.EXPIRED);
        }  catch (SignatureException e) {
            setAuthErrorResponse(response, TokenError.MALFORMED);
        } catch (UnsupportedJwtException e) {
            setAuthErrorResponse(response, TokenError.UNSUPPORTED);
        } catch (NullPointerException e) {
            setAuthErrorResponse(response, TokenError.EMPTY);
        } catch (Exception e) {
            System.out.println(CLASS_NAME + " - Exception class : " + e.getClass());
            System.out.println(CLASS_NAME + " - Exception message : " + e.getMessage());
            e.printStackTrace();
            setAuthErrorResponse(response, TokenError.INVALID);
        }
    }

    private String extractTokenFromAuthHeader(HttpServletRequest request) {
        String authHeader = request.getHeader(TokenConstant.AUTH_HEADER_NAME);

        if (authHeader == null) {
            throw new TokenException(TokenError.EMPTY);
        }
        if(authHeader.startsWith(TokenConstant.AUTH_TOKEN_NAME) == false) {
            throw new TokenException(TokenError.INVALID);
        }

        return authHeader.substring(7);
    }

    private String extractTokenFromCookie(HttpServletRequest request) {
        AtomicReference<String> authCookie = new AtomicReference<>();
        Arrays.stream(request.getCookies())
                .forEach((cookie) -> {
                    if(cookie.getName().equals(HeaderConstant.REFRESH_TOKEN_COOKIE)) {
                        authCookie.set(cookie.getValue());
                    }
                });

        if(authCookie.get() == null || authCookie.get().isBlank()) {
            throw new TokenException(TokenError.EMPTY);
        }

        return authCookie.get();
    }

    private void printHeaderReceived(HttpServletRequest request) {
        request.getHeaderNames()
                .asIterator()
                .forEachRemaining((headerName) -> {
                    System.out.println("Header :: " + headerName + " = " + request.getHeader(headerName));
                });
    }

}
