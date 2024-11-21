package com.visitor.petitefrance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import com.visitor.petitefrance.model.Destination;
import com.visitor.petitefrance.repository.DestinationRepository;
import com.visitor.petitefrance.exception.DestinationNotFoundException;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    // Get categories by state (This can be simplified or customized as needed)
    public List<String> getCategoriesByState(String state) {
        // Fetch categories dynamically based on the state
        return destinationRepository.findCategoriesByState(state);
    }

    // Get destinations by state and category
    public List<Destination> getDestinationsByStateAndCategory(String state, String category) {
        return destinationRepository.findDestinationsByStateAndCategory(state, category);
    }

    // Service method to get all destinations
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    // Service method to get a destination by its ID
    public Optional<Destination> getDestinationById(Long id) {
        return destinationRepository.findById(id);
    }

    // Service method to create a new destination
    public Destination createDestination(Destination destination) {
        // You could add additional validation here before saving the destination
        return destinationRepository.save(destination);
    }

    // Service method to update an existing destination
    public Destination updateDestination(Long id, Destination destinationDetails) {
        // Check if the destination exists
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        
        if (optionalDestination.isPresent()) {
            Destination existingDestination = optionalDestination.get();
            
            // Use BeanUtils.copyProperties for clean code if the property names match
            BeanUtils.copyProperties(destinationDetails, existingDestination, "id");  // Don't copy the ID as it is immutable

            // Save and return the updated destination
            return destinationRepository.save(existingDestination);
        } else {
            // If destination doesn't exist, throw a custom exception
            throw new DestinationNotFoundException("Destination with ID " + id + " does not exist.");
        }
    }

    // Service method to delete a destination by its ID
    public void deleteDestination(Long id) {
        if (destinationRepository.existsById(id)) {
            destinationRepository.deleteById(id);
        } else {
            // Throw custom exception if destination doesn't exist
            throw new DestinationNotFoundException("Destination with ID " + id + " does not exist.");
        }
    }
}
