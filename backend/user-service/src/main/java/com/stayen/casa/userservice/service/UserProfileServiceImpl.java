package com.stayen.casa.userservice.service;

import com.stayen.casa.userservice.dto.UserProfileDTO;
import com.stayen.casa.userservice.entity.UserProfile;
import com.stayen.casa.userservice.enums.AuthError;
import com.stayen.casa.userservice.exception.GeneralException;
import com.stayen.casa.userservice.exception.ProfileException;
import com.stayen.casa.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserRepository userRepository;

    @Autowired
    public UserProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserProfileDTO fetchUserProfile(String uid) {
        UserProfile userProfile = userRepository
                .findById(uid)
                .orElseThrow(() -> new ProfileException(AuthError.NO_ACCOUNT_FOUND));

        return new UserProfileDTO(userProfile);
    }

    @Override
    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) {
        Optional<UserProfile> anyPrevProfile = userRepository.findById(userProfileDTO.getUid());
        if(anyPrevProfile.isPresent()) {
            throw new ProfileException(AuthError.ACCOUNT_ALREADY_EXIST);
        }

        UserProfile userProfile = new UserProfile(userProfileDTO);
        userProfile = userRepository.save(userProfile);
        return new UserProfileDTO(userProfile);
    }

    @Override
    public UserProfileDTO updateUserProfile(String uid, UserProfileDTO userProfileDTO) {
        UserProfile userProfile = userRepository
                .findById(uid)
                .orElseThrow(() -> new ProfileException(AuthError.NO_ACCOUNT_FOUND));

        userProfile.update(userProfileDTO);

        userProfile = userRepository.save(userProfile);
        return new UserProfileDTO(userProfile);
    }

}
