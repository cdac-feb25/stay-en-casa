package com.stayen.casa.authenticationservice.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.stayen.casa.authenticationservice.config.MongoConfig;
import com.stayen.casa.authenticationservice.constant.ErrorConstant;
import com.stayen.casa.authenticationservice.dto.AuthResponseDTO;
import com.stayen.casa.authenticationservice.dto.BaseTimestampDTO;
import com.stayen.casa.authenticationservice.dto.LoginLogoutDTO;
import com.stayen.casa.authenticationservice.dto.LoginRequestDTO;
import com.stayen.casa.authenticationservice.dto.SignupRequestDTO;
import com.stayen.casa.authenticationservice.dto.SimpleResponseDTO;
import com.stayen.casa.authenticationservice.dto.AuthErrorDTO;
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
	private static final String CLASS_NAME = UserCredentialServiceImpl.class.getSimpleName();

//    private final MongoConfig mongoConfig;

	private final UserCredentialRepository userCredentialRepository;
	private final UserTokenService userTokenService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserCredentialServiceImpl(UserTokenService userTokenService,
			UserCredentialRepository userCredentialRepository, PasswordEncoder passwordEncoder) {
		this.userTokenService = userTokenService;
		this.userCredentialRepository = userCredentialRepository;
		this.passwordEncoder = passwordEncoder;
//		this.mongoConfig = mongoConfig;
	}
	
	/**
	 * 
	 * Logout Process 
	 * 
	 */
	public SimpleResponseDTO logoutUser() {
		return userTokenService.invalidateDeviceToken();
	}
	
	
	/**
	 * Validates the credentials of user with 
	 * the raw information entered by user on front-end
	 * 
	 * @param loginRequestDTO 
	 * @return UserCredential validated user credential
	 * @throws NoAccountFoundException if no account found with the given email
	 * @throws InvalidCredentialException if credential not matched with the credential stored in database
	 */
	private UserCredential verifyLoggedInUser(LoginRequestDTO loginRequestDTO) {
		Optional<UserCredential> credential = userCredentialRepository.findByEmail(loginRequestDTO.getEmail());
		
		if(credential.isEmpty()) {
			throw new NoAccountFoundException(ErrorConstant.NO_ACCOUNT_FOUND);
		}
		
//		if(loginRequestDTO instanceof LoginRequestDTO) {
//			LoginRequestDTO loginRequestDTO = (LoginRequestDTO) loginRequestDTO;
			
			if(passwordEncoder.matches(loginRequestDTO.getPassword(), credential.get().getPasswordHash()) == false) {
				throw new InvalidCredentialException(ErrorConstant.INVALID_CREDENTIAL);
			}
//		}
		
		return credential.get();
	}
	
	
	/**
	 * @throws InvalidCredentialException 
	 * @throws NoAccountFoundException 
	 * 
	 */
	@Override
	public AuthResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
		// Getting verified user credentials
		UserCredential credential = verifyLoggedInUser(loginRequestDTO);
		System.out.println(CLASS_NAME + " : user verified");
		
		JwtModel jwtModel = new JwtModel(credential.getUid(), loginRequestDTO.getEmail(), loginRequestDTO.getDeviceId());
		
		AuthResponseDTO authResponseDTO = userTokenService.generateToken(jwtModel);
		System.out.println(CLASS_NAME + " : token generated");
		return authResponseDTO;
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
		
		/**
		 * Creating new User based on 
		 * Details received from registration form
		 */
		String newUid = UUID.nameUUIDFromBytes(signupRequestDTO.getEmail().getBytes()).toString();
		String passwordHash = passwordEncoder.encode(signupRequestDTO.getPassword());
		
		return new UserCredential(newUid, signupRequestDTO.getEmail(), passwordHash);
	}
	

	/**
	 * 
	 */
	@Override
	public AuthResponseDTO registerNewUser(SignupRequestDTO signupRequestDTO) {
		UserCredential credential = createNewUserCredential(signupRequestDTO);
		
		System.out.println(CLASS_NAME + "New User Creation : ");
		System.out.println(CLASS_NAME + "User cred : " + credential);
		userCredentialRepository.save(credential);
		
		JwtModel jwtModel = new JwtModel(credential.getUid(), credential.getEmail(), signupRequestDTO.getDeviceId());
		
		AuthResponseDTO authResponseDTO = userTokenService.generateToken(jwtModel);
		return authResponseDTO;
	}
	
}
