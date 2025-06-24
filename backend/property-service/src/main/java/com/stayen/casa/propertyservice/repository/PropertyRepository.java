package com.stayen.casa.propertyservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stayen.casa.propertyservice.entity.PropertyEntity;

@Repository
public interface PropertyRepository extends MongoRepository<PropertyEntity, String>{

	Optional<PropertyEntity> findByOwnerIdAndLocationAddressAndLocationPincode(String ownerID, String address, Integer pincode);

}
