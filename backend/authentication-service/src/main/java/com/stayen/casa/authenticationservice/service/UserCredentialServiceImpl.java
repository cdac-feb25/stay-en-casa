package com.stayen.casa.authenticationservice.service;

import java.util.Optional;
import java.util.UUID;

import com.stayen.casa.authenticationservice.dto.LogoutRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.stayen.casa.authenticationservice.dto.AuthTokenResponseDTO;
import com.stayen.casa.authenticationservice.dto.LoginSignupRequestDTO;
import com.stayen.casa.authenticationservice.dto.SimpleResponseDTO;
import com.stayen.casa.authenticationservice.entity.UserCredential;
import com.stayen.casa.authenticationservice.enums.AuthError;
import com.stayen.casa.authenticationservice.exception.AuthException;
import com.stayen.casa.authenticationservice.model.JwtModel;
import com.stayen.casa.authenticationservice.repository.UserCredentialRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {
	private static final String CLASS_NAME = UserCredentialServiceImpl.class.getSimpleName();

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
	public SimpleResponseDTO logoutUser(LogoutRequestDTO logoutRequestDTO) {
		return userTokenService.invalidateDeviceToken(logoutRequestDTO);
	}
	
	/**
	 *
	 */
	@Override
	public AuthTokenResponseDTO loginUser(LoginSignupRequestDTO loginSignupRequestDTO) {
		// Getting verified user credentials
		UserCredential credential = verifyUser(loginSignupRequestDTO);
		System.out.println(CLASS_NAME + " : user verified");
		
		JwtModel jwtModel = new JwtModel(credential.getUid(), loginSignupRequestDTO.getEmail(), loginSignupRequestDTO.getDeviceId());
		
		AuthTokenResponseDTO authResponseDTO = userTokenService.generateToken(jwtModel);
		System.out.println(CLASS_NAME + " : token generated");
		return authResponseDTO;
	}
	
	/**
	 * 
	 */
	@Override
	public AuthTokenResponseDTO signupUser(LoginSignupRequestDTO loginSignupRequestDTO) {
		UserCredential credential = createNewUserCredential(loginSignupRequestDTO);
		
		System.out.println(CLASS_NAME + "New User Creation : ");
		System.out.println(CLASS_NAME + "User cred : " + credential);
		userCredentialRepository.save(credential);
		
		JwtModel jwtModel = new JwtModel(credential.getUid(), credential.getEmail(), loginSignupRequestDTO.getDeviceId());
		
		AuthTokenResponseDTO authResponseDTO = userTokenService.generateToken(jwtModel);
		return authResponseDTO;
	}
	
	/**
	 * Validates the credentials of user with 
	 * the raw information entered by user on front-end
	 * 
	 * @param loginSignupRequestDTO
	 * @return UserCredential validated user credential
	 * @throws AuthException if no account found with the given email / if credential not matched with the credential stored in database
	 */
	private UserCredential verifyUser(LoginSignupRequestDTO loginSignupRequestDTO) {
		Optional<UserCredential> credential = userCredentialRepository.findByEmail(loginSignupRequestDTO.getEmail());
		
		if(credential.isEmpty()) {
			throw new AuthException(AuthError.NO_ACCOUNT_FOUND);
		}
		
		if(!passwordEncoder.matches(loginSignupRequestDTO.getPassword(), credential.get().getPasswordHash())) {
			throw new AuthException(AuthError.INVALID_CREDENTIAL);
		}
		
		return credential.get();
	}
	
	
	/**
	 * 
	 * Login Process Above ^
	 * 
	 * 
	 * Registration Process Below v
	 * 
	 */
	
	private UserCredential createNewUserCredential(LoginSignupRequestDTO loginSignupRequestDTO) {
		Optional<UserCredential> credential = userCredentialRepository.findByEmail(loginSignupRequestDTO.getEmail());

		if(credential.isPresent()) {
			throw new AuthException(AuthError.ACCOUNT_ALREADY_EXIST);
		}
		
		// TODO : optional, check if user is disabled or not
		
		/**
		 * Creating new User based on 
		 * Details received from registration form
		 */
		String newUid = UUID.nameUUIDFromBytes(loginSignupRequestDTO.getEmail().getBytes()).toString();
		String passwordHash = passwordEncoder.encode(loginSignupRequestDTO.getPassword());
		
		return new UserCredential(newUid, loginSignupRequestDTO.getEmail(), passwordHash);
	}

	
}
