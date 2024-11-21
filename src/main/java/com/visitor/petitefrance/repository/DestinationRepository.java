package com.visitor.petitefrance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.visitor.petitefrance.model.Destination;    

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    
    // Find destinations by state (e.g., Puducherry, Karaikal, etc.)
    List<Destination> findByState(String state);

    // Find categories by state (we assume the category is part of the Destination model)
    List<String> findCategoriesByState(String state);

    // Find destinations by state and category (e.g., Nature, Heritage, etc.)
    List<Destination> findByStateAndCategory(String state, String category);

    public List<Destination> findDestinationsByStateAndCategory(String state, String category);
}