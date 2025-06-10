package com.stayen.casa.authenticationservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stayen.casa.authenticationservice.constant.ErrorConstant;
import com.stayen.casa.authenticationservice.dto.LogoutDTO;
import com.stayen.casa.authenticationservice.dto.TokenResponseDTO;
import com.stayen.casa.authenticationservice.entity.UserCredential;
import com.stayen.casa.authenticationservice.entity.UserToken;
import com.stayen.casa.authenticationservice.exception.credential.NoAccountFoundException;
import com.stayen.casa.authenticationservice.exception.credential.SessionNotFoundException;
import com.stayen.casa.authenticationservice.entity.DeviceToken;
import com.stayen.casa.authenticationservice.model.JwtModel;
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
	
	private UserToken getOrDefaultUserToken(String email) {
		Optional<UserToken> userToken = userTokenRepository.findByEmail(email);

		if (userToken.isPresent()) {
			return userToken.get();
		} else {
			return new UserToken(email);
		}
	}
	
	@Override
	public UserToken getValidUserToken(String email) {
		return userTokenRepository.findByEmail(email).orElseThrow(() -> {
			return new NoAccountFoundException(ErrorConstant.NO_ACCOUNT_FOUND);
		});
	}
	
	@Override
	public LogoutDTO invalidateDeviceToken(LogoutDTO logoutDTO) {
		UserToken userToken = getValidUserToken(logoutDTO.getEmail());
		
		if(userToken.removeDeviceToken(logoutDTO.getDeviceId()) == false) {
			throw new SessionNotFoundException(ErrorConstant.SESSION_NOT_FOUND);
		}
		
		userToken = userTokenRepository.save(userToken);
		
		return logoutDTO.fromUpdatedTimestamp();
	}

	/**
	 * Generating new tokens (Access + Refresh),
	 * 
	 * and saving it in DB
	 * 
	 * @param UserCredential
	 * @return StriTokenResponseDTOng
	 */
	@Override
	public TokenResponseDTO generateToken(JwtModel jwtModel) {
		UserToken userToken = getOrDefaultUserToken(jwtModel.getEmail());
		
		String newAccessToken = jwtUtils.generateAccessToken(jwtModel);
		String newRefreshToken = jwtUtils.generateRefreshToken(jwtModel);
		String tempToken = jwtUtils.generateRefreshToken(jwtModel);

		/**
		 * 
		 */
		userToken.removeDeviceToken(jwtModel.getDeviceId());
		
		userToken.addDeviceToken(new DeviceToken(jwtModel.getDeviceId(), newRefreshToken, tempToken));
		
		userTokenRepository.save(userToken);

		return TokenResponseDTO.from(jwtModel, newAccessToken, newRefreshToken);
	}

}
