package com.visitor.petitefrance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visitor.petitefrance.model.Itinerary;
import com.visitor.petitefrance.service.ItineraryService;

@RestController
@RequestMapping("/itineraries")
@CrossOrigin(origins = "http://localhost:3000/")
public class ItineraryController {
    @Autowired
    private ItineraryService itineraryService;

    @GetMapping("/{userId}")
    public List<Itinerary> getUserItineraries(@PathVariable String userId) {
        return itineraryService.getItinerariesByUserId(userId);
    }

    @PostMapping("/create")
    public Itinerary createItinerary(@RequestBody Itinerary itinerary) {
        return itineraryService.createItinerary(itinerary);
    }

    @PutMapping("/{itineraryId}/day/{dayNumber}/destination/{destinationName}/complete")
    public void completeDestination(@PathVariable String itineraryId, @PathVariable int dayNumber, 
                                     @PathVariable String destinationName) {
        itineraryService.updateDestinationCompletion(itineraryId, dayNumber, destinationName);
    }

    @DeleteMapping("/{itineraryId}")
    public void deleteItinerary(@PathVariable String itineraryId) {
        itineraryService.deleteItinerary(itineraryId);
    }
}