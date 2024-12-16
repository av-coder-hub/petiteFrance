package com.visitor.petitefrance.repository;

// import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.visitor.petitefrance.model.District;

public interface DistrictRepository extends MongoRepository<District, String> {
    // List<District> findByName(String name); // Query to find districts by name
    Optional<District> findByNameIgnoreCase(String name);
}