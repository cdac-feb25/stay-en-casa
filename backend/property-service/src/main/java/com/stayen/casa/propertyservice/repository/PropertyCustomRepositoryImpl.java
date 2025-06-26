package com.stayen.casa.propertyservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.stayen.casa.propertyservice.dto.PropertySearchRequest;
import com.stayen.casa.propertyservice.entity.PropertyEntity;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class PropertyCustomRepositoryImpl implements PropertyCustomRepository {
	
	private final MongoTemplate mongoTemplate;

	@Override
	public List<PropertyEntity> searchProperties(PropertySearchRequest searchFields) {
		
		List<Criteria> filters = new ArrayList<>();
		
		if(searchFields.getPropertyName()!=null && !searchFields.getPropertyName().isBlank())
		{
			filters.add(Criteria.where("propertyName").regex(searchFields.getPropertyName(), "i"));
		}
		
		if(searchFields.getListingType()!=null)
		{
			filters.add(Criteria.where("listingType").is(searchFields.getListingType()));
		}
		
		 if (searchFields.getFurnishing() != null) {
	            filters.add(Criteria.where("furnishing").is(searchFields.getFurnishing()));
	        }
		
		if(searchFields.getPropertyCategory()!=null)
		{
			filters.add(Criteria.where("propertyCategory").is(searchFields.getPropertyCategory()));
		}
		
		if(searchFields.getMinPrice()!=null && searchFields.getMaxPrice()!=null)
		{
			filters.add(Criteria.where("price").gte(searchFields.getMinPrice().doubleValue()).lte(searchFields.getMaxPrice().doubleValue()));
		}else if(searchFields.getMinPrice()!=null)
		{
			filters.add(Criteria.where("price").gte(searchFields.getMinPrice().doubleValue()));
		}else if(searchFields.getMaxPrice()!=null)
		{
			filters.add(Criteria.where("price").lte(searchFields.getMaxPrice().doubleValue()));
		}
		
		if(searchFields.getMinArea()!=null && searchFields.getMaxArea()!=null)
		{
			filters.add(Criteria.where("area").gte(searchFields.getMinArea()).lte(searchFields.getMaxArea()));
		}else if(searchFields.getMinArea()!=null)
		{
			filters.add(Criteria.where("area").gte(searchFields.getMinArea()));
		}else if(searchFields.getMaxArea()!=null)
		{
			filters.add(Criteria.where("area").lte(searchFields.getMaxArea()));
		}
		
		if(searchFields.getUnit()!=null)
		{
			filters.add(Criteria.where("unit").is(searchFields.getUnit()));
		}
		
		if(searchFields.getBedrooms()!=null)
		{
			filters.add(Criteria.where("bedrooms").gte(searchFields.getBedrooms()));
		}
		
		if(searchFields.getBathrooms()!=null)
		{
			filters.add(Criteria.where("bathrooms").gte(searchFields.getBathrooms()));
		}
		
		if(searchFields.getAmenities()!=null && !searchFields.getAmenities().isEmpty())
		{
			filters.add(Criteria.where("amenities").in(searchFields.getAmenities()));
		}
		
		if(searchFields.getTotalFloors()!=null)
		{
			filters.add(Criteria.where("totalFloors").gte(searchFields.getTotalFloors()));
		}
		
		if(searchFields.getCity()!=null && !searchFields.getCity().isBlank())
		{
			filters.add(Criteria.where("location.city").regex(searchFields.getCity(), "i"));
		}
		
		if(searchFields.getState()!=null && !searchFields.getState().isBlank())
		{
			filters.add(Criteria.where("location.state").regex(searchFields.getState(), "i"));
		}
		
		if(searchFields.getLocality()!=null && !searchFields.getLocality().isBlank())
		{
			filters.add(Criteria.where("location.locality").regex(searchFields.getLocality(), "i"));
		}
		
		Query query = new Query();
		
		if(!filters.isEmpty())
		{
			query.addCriteria(new Criteria().andOperator(filters.toArray(new Criteria[0])));
		}
		
		return mongoTemplate.find(query, PropertyEntity.class);
	}

}
