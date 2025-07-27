package com.stayen.casa.userservice.service;

import com.stayen.casa.userservice.dto.UserProfileDTO;

public interface UserProfileService {

    UserProfileDTO fetchUserProfile(String uid);

    UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO);

    UserProfileDTO updateUserProfile(String uid, UserProfileDTO userProfileDTO);


}
