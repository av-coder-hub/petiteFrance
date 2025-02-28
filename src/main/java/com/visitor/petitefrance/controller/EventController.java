package com.visitor.petitefrance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visitor.petitefrance.model.Event;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private MongoTemplate mongoTemplate;

    // Get all events from the MongoDB collection
    @GetMapping
    public List<Event> getAllEvents() {
        // Fetch all events from the "events" collection
        return mongoTemplate.findAll(Event.class, "events");
    }

    // Add a new event to the MongoDB collection
    @PostMapping("/add")
    public ResponseEntity<String> addEvent(@RequestBody Event newEvent) {
        // Insert the new event into the "events" collection
        mongoTemplate.insert(newEvent, "events");
        return ResponseEntity.ok("Event added successfully!");
    }
}
