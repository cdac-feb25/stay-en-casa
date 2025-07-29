package com.stayen.casa.userservice.service;

import com.stayen.casa.userservice.dto.UserProfileDTO;
import com.stayen.casa.userservice.entity.UserProfile;
import com.stayen.casa.userservice.enums.ProfileError;
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
                .orElseThrow(() -> new ProfileException(ProfileError.NO_PROFILE_FOUND));

        return new UserProfileDTO(userProfile);
    }

    @Override
    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) {
        Optional<UserProfile> anyPrevProfile = userRepository.findById(userProfileDTO.getUid());
        if(anyPrevProfile.isPresent()) {
            throw new ProfileException(ProfileError.PROFILE_ALREADY_CREATED);
        }

        UserProfile userProfile = new UserProfile(userProfileDTO);
        userProfile = userRepository.save(userProfile);
        return new UserProfileDTO(userProfile);
    }

    @Override
    public UserProfileDTO updateUserProfile(String uid, UserProfileDTO updatedUserProfileDTO) {
        UserProfile userProfile = userRepository
                .findById(uid)
                .orElseThrow(() -> new ProfileException(ProfileError.NO_PROFILE_FOUND));

        userProfile.update(updatedUserProfileDTO);

        userProfile = userRepository.save(userProfile);
        return new UserProfileDTO(userProfile);
    }

}
