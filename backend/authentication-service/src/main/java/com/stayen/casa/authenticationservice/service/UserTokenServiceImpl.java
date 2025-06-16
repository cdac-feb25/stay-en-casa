package com.stayen.casa.authenticationservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.stayen.casa.authenticationservice.constant.TokenConstant;
import com.stayen.casa.authenticationservice.constant.ErrorConstant;
import com.stayen.casa.authenticationservice.constant.UserConstant;
import com.stayen.casa.authenticationservice.dto.AuthResponseDTO;
import com.stayen.casa.authenticationservice.dto.SimpleResponseDTO;
import com.stayen.casa.authenticationservice.dto.AuthErrorDTO;
import com.stayen.casa.authenticationservice.entity.UserCredential;
import com.stayen.casa.authenticationservice.entity.UserToken;
import com.stayen.casa.authenticationservice.enums.AuthError;
import com.stayen.casa.authenticationservice.enums.TokenError;
import com.stayen.casa.authenticationservice.enums.TokenType;
import com.stayen.casa.authenticationservice.exception.credential.AuthException;
import com.stayen.casa.authenticationservice.exception.token.TokenException;
import com.stayen.casa.authenticationservice.entity.DeviceToken;
import com.stayen.casa.authenticationservice.model.JwtModel;
import com.stayen.casa.authenticationservice.model.User;
import com.stayen.casa.authenticationservice.repository.UserTokenRepository;
import com.stayen.casa.authenticationservice.security.utils.JwtUtils;

@Service
public class UserTokenServiceImpl implements UserTokenService {

	private final JwtUtils jwtUtils;
	private final UserTokenRepository userTokenRepository;

	@Autowired
	public UserTokenServiceImpl(JwtUtils jwtUtils, UserTokenRepository userTokenRepository) {
		this.jwtUtils = jwtUtils;
		this.userTokenRepository = userTokenRepository;
	}
	
	private UserToken getOrDefaultUserToken(JwtModel jwtModel) {
		Optional<UserToken> userToken = userTokenRepository.findByUid(jwtModel.getUid());

		if (userToken.isPresent()) {
			return userToken.get();
		} else {
			return new UserToken(jwtModel.getUid(), jwtModel.getEmail());
		}
	}
	
	@Override
	public UserToken fetchUserToken(String uid, RuntimeException throwException) {
		return userTokenRepository.findByUid(uid).orElseThrow(() -> {
			return throwException;
		});
	}
	
	@Override
	public SimpleResponseDTO invalidateDeviceToken() {
		/**
		 * If no logged in user found from JWT Authorization
		 * throws InvalidTokenException
		 */
		User loggedInUser = UserConstant.getLoggedInUser();
	
		/**
		 * If no user token found
		 */
		UserToken userToken = fetchUserToken(
				loggedInUser.getUid(), 
				new AuthException(AuthError.NO_ACCOUNT_FOUND)
		);
		
		/**
		 * If token is valid, 
		 * but user has already logged out.
		 */
		if(userToken.removeDeviceToken(loggedInUser.getDeviceId()) == false) {
			throw new AuthException(AuthError.SESSION_NOT_FOUND);
		}
		
		/**
		 * Saving Removed Device Token
		 */
		userToken = userTokenRepository.save(userToken);
		
		return new SimpleResponseDTO(TokenConstant.LOGOUT_MSG);
	}


	/**
	 * Generating new tokens (Access + Refresh),
	 * 
	 * and saving it in DB
	 * 
	 * @param jwtModel
	 * @return TokenResponseDTO
	 */
	@Override
	public AuthResponseDTO generateToken(JwtModel jwtModel) {
		UserToken userToken = getOrDefaultUserToken(jwtModel);
		
		String newAccessToken = jwtUtils.generateJwtToken(jwtModel, TokenType.ACCESS);
		String newRefreshToken = jwtUtils.generateJwtToken(jwtModel, TokenType.REFRESH);
		String lastRefreshToken = jwtUtils.generateJwtToken(jwtModel, TokenType.TEMP);

		/**
		 * 
		 */
		userToken.removeDeviceToken(jwtModel.getDeviceId());
		userToken.addDeviceToken(new DeviceToken(jwtModel.getDeviceId(), newRefreshToken, lastRefreshToken));
		
		/**
		 * Saving New Access, Refresh Token
		 */
		userTokenRepository.save(userToken);
		return new AuthResponseDTO(jwtModel.getUid(), newAccessToken, newRefreshToken);
	}

	/**
	 * 
	 */
	@Override
	public AuthResponseDTO refreshToken() {
		User loggedInUser = UserConstant.getLoggedInUser();
		
		UserToken userToken = fetchUserToken(
				loggedInUser.getUid(), 
				new AuthException(AuthError.SESSION_NOT_FOUND)
		);
		
		//
		return rotateAndGenerateRefreshToken(loggedInUser, userToken);
	}
	
	private AuthResponseDTO rotateAndGenerateRefreshToken(User loggedInUser, UserToken userToken) {
		
		// TODO : this is never intercepted
		// because if last refresh token is expired then it never got validated
		if(userToken.isLastRefreshToken(loggedInUser.getToken())) {
			
			// TODO: Invalidate current login 
			
			throw new TokenException(TokenError.BLOCKED);
		}
		
		if(userToken.ifRefreshAndRotated(loggedInUser.getToken())) {
			JwtModel jwtModel = new JwtModel(loggedInUser.getUid(), loggedInUser.getEmail(), loggedInUser.getDeviceId());
			
			String newAccessToken = jwtUtils.generateJwtToken(jwtModel, TokenType.ACCESS);
			String newRefreshToken = jwtUtils.generateJwtToken(jwtModel, TokenType.REFRESH);
			
			userToken.setNewRefreshToken(loggedInUser.getDeviceId(), newRefreshToken);
			
			/**
			 * Saving Rotated Refresh Token
			 */
			userTokenRepository.save(userToken);
			
			return new AuthResponseDTO(loggedInUser.getUid(), newAccessToken, newRefreshToken);
		} else {
			throw new TokenException(TokenError.INVALID);
		}
	}
	
	

}
