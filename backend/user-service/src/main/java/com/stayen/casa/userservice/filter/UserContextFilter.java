package com.stayen.casa.userservice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stayen.casa.userservice.dto.ErrorResponseDTO;
import com.stayen.casa.userservice.enums.CommonError;
import com.stayen.casa.userservice.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserContextFilter extends OncePerRequestFilter {

    private static final Map<String, String> PATH_ALLOWED_FOR_NO_USER_HEADER;
    static {
        PATH_ALLOWED_FOR_NO_USER_HEADER = new HashMap<>();
        PATH_ALLOWED_FOR_NO_USER_HEADER.put("/users/profile", "POST");
    }

    private ObjectMapper mapper;

    @Autowired
    public UserContextFilter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Setting the response header, status, and message to return
     * only when request header does not have user specific details
     */
    private void setErrorResponse(HttpServletResponse response, CommonError commonError) throws IOException {
        response.setContentType("application/json");

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(mapper.writeValueAsString(new ErrorResponseDTO(commonError)));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        String method = request.getMethod();

        return PATH_ALLOWED_FOR_NO_USER_HEADER.entrySet()
                .stream()
                .anyMatch((entry) -> {
                    return (path.equalsIgnoreCase(entry.getKey()) && method.equalsIgnoreCase(entry.getValue()));
                });
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uid = request.getHeader("User-Id");
        String email = request.getHeader("User-Email");
        String deviceId = request.getHeader("User-Device-Id");

        if(uid != null && email != null && deviceId != null && !uid.isBlank() && !email.isBlank() && !deviceId.isBlank()) {
            User user = new User(uid, email, deviceId);

            UsernamePasswordAuthenticationToken verifiedUser = new UsernamePasswordAuthenticationToken(uid, null, null);
            verifiedUser.setDetails(user);

            SecurityContextHolder.getContext().setAuthentication(verifiedUser);
            filterChain.doFilter(request, response);
        } else {
            setErrorResponse(response, CommonError.USER_DETAIL_NOT_FOUND);
        }
    }
}
