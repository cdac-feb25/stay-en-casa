package com.stayen.casa.authenticationservice.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("user_credentials")
@AllArgsConstructor
@Getter
@Setter
public class UserCredential extends BaseEntity {
	
	@Id
//	@Indexed(unique = true)
	@Field(name = "email")
	private String email;
	
	private String passwordHash;
}
