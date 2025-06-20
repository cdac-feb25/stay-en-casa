package com.stayen.casa.propertyservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stayen.casa.propertyservice.entity.PropertyEntity;

public interface PropertyRepository extends MongoRepository<PropertyEntity, String>{

	Optional<PropertyEntity> findByOwnerIdAndLocationAddressAndLocationPincode(String ownerID, String address, Integer pincode);

}
