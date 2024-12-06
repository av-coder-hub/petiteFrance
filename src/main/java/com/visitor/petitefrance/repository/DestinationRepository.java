package com.visitor.petitefrance.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.visitor.petitefrance.model.Destination;

public interface DestinationRepository extends MongoRepository<Destination, String> {
    // Find destinations by district
    List<Destination> findByDistrict(String district);
    
    List<Destination> findByCategory(String category);
}
