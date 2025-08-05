package com.stayen.casa.userservice.service;

import com.stayen.casa.userservice.dto.UserProfileDTO;
import com.stayen.casa.userservice.model.User;

public interface UserProfileService {

    UserProfileDTO fetchUserProfile(String uid);

    UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO);

    UserProfileDTO updateUserProfile(User loggedInUser, UserProfileDTO updatedUserProfileDTO);


}
