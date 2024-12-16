package com.visitor.petitefrance.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.visitor.petitefrance.model.Itinerary;

public interface ItineraryRepository extends MongoRepository<Itinerary, String> {
    List<Itinerary> findByUserId(String userId); // Custom query to find itineraries by user
}