package com.stayen.casa.userservice.entity;

import com.stayen.casa.userservice.dto.UserProfileDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "user_profiles")
public class UserProfile extends BaseTimestampEntity {

    @MongoId
    private String uid;

    @Indexed(unique = true)
    private String email;

    private String name;

    private String phoneNo;

    private String address;

    private String photoUrl;

    public UserProfile(UserProfileDTO userProfileDTO) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.uid = userProfileDTO.getUid();
        this.email = userProfileDTO.getEmail();
        this.name = userProfileDTO.getName();
        this.phoneNo = userProfileDTO.getPhoneNo();
        this.address = userProfileDTO.getAddress();
        this.photoUrl = userProfileDTO.getPhotoUrl();
    }

    public UserProfile(String uid, String email, String name, String phoneNo, String address, String photoUrl) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.photoUrl = photoUrl;
    }

    public UserProfile(String uid, String email, String name, String phoneNo, String address, String photoUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.photoUrl = photoUrl;
    }

    /**
     * Uid, Email cannot be updated
     */
    public void updateTimestamp(UserProfileDTO updatedUserProfileDTO) {
        this.name = updatedUserProfileDTO.getName();
        this.phoneNo = updatedUserProfileDTO.getPhoneNo();
        this.address = updatedUserProfileDTO.getAddress();
        this.photoUrl = updatedUserProfileDTO.getPhotoUrl();
        this.setUpdatedAt(LocalDateTime.now());
    }
}
