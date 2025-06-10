package com.stayen.casa.authenticationservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stayen.casa.authenticationservice.constant.ErrorConstant;
import com.stayen.casa.authenticationservice.dto.AuthResponseDTO;
import com.stayen.casa.authenticationservice.dto.BaseDTO;
import com.stayen.casa.authenticationservice.dto.LoginLogoutDTO;
import com.stayen.casa.authenticationservice.dto.LoginRequestDTO;
import com.stayen.casa.authenticationservice.dto.LogoutDTO;
import com.stayen.casa.authenticationservice.dto.SignupRequestDTO;
import com.stayen.casa.authenticationservice.dto.TokenResponseDTO;
import com.stayen.casa.authenticationservice.entity.UserCredential;
import com.stayen.casa.authenticationservice.entity.UserToken;
import com.stayen.casa.authenticationservice.exception.credential.AccountAlreadyExistException;
import com.stayen.casa.authenticationservice.exception.credential.InvalidCredentialException;
import com.stayen.casa.authenticationservice.exception.credential.NoAccountFoundException;
import com.stayen.casa.authenticationservice.model.JwtModel;
import com.stayen.casa.authenticationservice.repository.UserCredentialRepository;
import com.stayen.casa.authenticationservice.repository.UserTokenRepository;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {

	private final UserCredentialRepository userCredentialRepository;
	private final UserTokenService userTokenService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserCredentialServiceImpl(UserTokenService userTokenService,
			UserCredentialRepository userCredentialRepository, PasswordEncoder passwordEncoder) {
		this.userTokenService = userTokenService;
		this.userCredentialRepository = userCredentialRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * 
	 * Logout Process 
	 * 
	 */
	public LogoutDTO logoutUser(LogoutDTO logoutDTO) {
		return userTokenService.invalidateDeviceToken(logoutDTO);
	}
	
	
	/**
	 * Validates the credentials of user with 
	 * the raw information entered by user on front-end
	 * 
	 * @param loginLogoutDTO 
	 * @return UserCredential validated user credential
	 * @throws NoAccountFoundException if no account found with the given email
	 * @throws InvalidCredentialException if credential not matched with the credential stored in database
	 */
	private UserCredential verifyLoggedInUser(LoginLogoutDTO loginLogoutDTO) {
		Optional<UserCredential> credential = userCredentialRepository.findByEmail(loginLogoutDTO.getEmail());
		
		if(credential.isEmpty()) {
			throw new NoAccountFoundException(ErrorConstant.NO_ACCOUNT_FOUND);
		}
		
		if(loginLogoutDTO instanceof LoginRequestDTO) {
			LoginRequestDTO loginRequestDTO = (LoginRequestDTO) loginLogoutDTO;
			
			if(passwordEncoder.matches(loginRequestDTO.getPassword(), credential.get().getPasswordHash()) == false) {
				throw new InvalidCredentialException(ErrorConstant.INVALID_CREDENTIAL);
			}
		}
		
		return credential.get();
	}
	
	
	/**
	 * @throws InvalidCredentialException 
	 * @throws NoAccountFoundException 
	 * 
	 */
	@Override
	public TokenResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
		/**
		 * Getting verified user credentials
		 */
		UserCredential credential = verifyLoggedInUser(loginRequestDTO);
		
		JwtModel jwtModel = new JwtModel(credential.getEmail(), loginRequestDTO.getDeviceId());
		
		TokenResponseDTO tokenResponseDTO = userTokenService.generateToken(jwtModel);
		return tokenResponseDTO;
	}
	
	/**
	 * 
	 * Login Process Above ^
	 * 
	 * 
	 * Registration Process Below v
	 * 
	 */
	
	private UserCredential createNewUserCredential(SignupRequestDTO signupRequestDTO) {
		Optional<UserCredential> credential = userCredentialRepository.findByEmail(signupRequestDTO.getEmail());

		if(credential.isPresent()) {
			throw new AccountAlreadyExistException(ErrorConstant.ACCOUNT_ALREADY_EXIST);
		}
		
		// TODO : optional, check if user is disabled or not
		
		return new UserCredential(signupRequestDTO.getEmail(), passwordEncoder.encode(signupRequestDTO.getPassword()));
	}
	

	/**
	 * 
	 */
	@Override
	public TokenResponseDTO registerNewUser(SignupRequestDTO signupRequestDTO) {
		UserCredential credential = createNewUserCredential(signupRequestDTO);
		
		userCredentialRepository.save(credential);
		
		JwtModel jwtModel = new JwtModel(credential.getEmail(), signupRequestDTO.getDeviceId());
		
		TokenResponseDTO tokenResponseDTO = userTokenService.generateToken(jwtModel);
		return tokenResponseDTO;
	}
	
}
