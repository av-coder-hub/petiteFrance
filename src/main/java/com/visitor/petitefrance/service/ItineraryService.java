package com.visitor.petitefrance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitor.petitefrance.model.Itinerary;
import com.visitor.petitefrance.repository.ItineraryRepository;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    public List<Itinerary> getItinerariesByUserId(String userId) {
        return itineraryRepository.findByUserId(userId);
    }

    public Itinerary createItinerary(Itinerary itinerary) {
        // Validate max days and destinations
        if (itinerary.getDays().size() > 7) {
            throw new IllegalArgumentException("An itinerary cannot exceed 7 days.");
        }
        return itineraryRepository.save(itinerary);
    }

    public void updateDestinationCompletion(String itineraryId, int dayNumber, String destinationName) {
        Itinerary itinerary = itineraryRepository.findById(itineraryId).orElseThrow(
                () -> new IllegalArgumentException("Itinerary not found")
        );

        itinerary.getDays().forEach(dayPlan -> {
            if (dayPlan.getDayNumber() == dayNumber) {
                dayPlan.getDestinations().forEach(destination -> {
                    if (destination.getName().equals(destinationName)) {
                        destination.setCompleted(true);
                    }
                });
            }
        });

        itineraryRepository.save(itinerary);
    }

    public void deleteItinerary(String itineraryId) {
        itineraryRepository.deleteById(itineraryId);
    }
}
