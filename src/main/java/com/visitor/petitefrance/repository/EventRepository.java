package com.visitor.petitefrance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.visitor.petitefrance.model.Event;

public interface EventRepository extends MongoRepository<Event, String> {
    // You can define custom queries here if needed
}
