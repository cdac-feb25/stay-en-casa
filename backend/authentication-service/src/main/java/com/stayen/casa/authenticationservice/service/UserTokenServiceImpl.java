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
import com.stayen.casa.authenticationservice.enums.TokenType;
import com.stayen.casa.authenticationservice.exception.credential.NoAccountFoundException;
import com.stayen.casa.authenticationservice.exception.credential.SessionNotFoundException;
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
	public UserToken fetchUserToken(String uid) {
		return userTokenRepository.findByUid(uid).orElseThrow(() -> {
			return new NoAccountFoundException(ErrorConstant.NO_ACCOUNT_FOUND);
		});
	}
	
	@Override
	public SimpleResponseDTO invalidateDeviceToken() {
		User user = UserConstant.getLoggedInUser();
	
		UserToken userToken = fetchUserToken(user.getUid());
		
		if(userToken.removeDeviceToken(user.getDeviceId()) == false) {
			throw new SessionNotFoundException(ErrorConstant.SESSION_NOT_FOUND);
		}
		
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
		String tempToken = jwtUtils.generateJwtToken(jwtModel, TokenType.TEMP);

		/**
		 * 
		 */
		userToken.removeDeviceToken(jwtModel.getDeviceId());
		
		userToken.addDeviceToken(new DeviceToken(jwtModel.getDeviceId(), newRefreshToken, tempToken));
		
		userTokenRepository.save(userToken);

		return AuthResponseDTO.from(jwtModel, newAccessToken, newRefreshToken);
	}

}
