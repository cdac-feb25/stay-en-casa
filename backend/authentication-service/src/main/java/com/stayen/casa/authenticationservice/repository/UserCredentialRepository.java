package com.stayen.casa.authenticationservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stayen.casa.authenticationservice.entity.UserCredential;

public interface UserCredentialRepository extends MongoRepository<UserCredential, String> {
	
	Optional<UserCredential> findByUidAndEmail(String id, String email);
	
	Optional<UserCredential> findByEmail(String email);
	
}
