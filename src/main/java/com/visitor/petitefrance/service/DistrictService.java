package com.visitor.petitefrance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitor.petitefrance.model.Destination;
import com.visitor.petitefrance.model.District;
import com.visitor.petitefrance.repository.DistrictRepository;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    // Method to get all districts (including embedded destinations)
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public List<District> findAllDistricts() {
        return districtRepository.findAll();
    }

    // Method to get a district by name (to fetch district with destinations for /districts/{districtname})
    public Optional<District> getDistrictByName(String name) {
        return districtRepository.findByNameIgnoreCase(name.toLowerCase());
    }

    public boolean addDestinationToDistrict(String districtId, Destination newDestination) {
        Optional<District> districtOptional = districtRepository.findById(districtId);
        if (districtOptional.isPresent()) {
            District district = districtOptional.get();
            district.getDestinations().add(newDestination); // Add the new destination to the list
            districtRepository.save(district); // Save the updated district back to the database
            return true;
        }
        return false;
    }
}


// @Service
// public class DestinationService {

//     @Autowired
//     private DestinationRepository destinationRepository;

//     // Get all destinations
//     public List<Destination> getDestinationsByDistrict(String district) {
//         return destinationRepository.findByDistrict(district);
//     }

//     // Add a new destination
//     public Destination addDestination(Destination destination) {
//         return destinationRepository.save(destination);
//     }
//     public Destination saveDestination(Destination destination) {
//         if (destination.getTemperature() == null) {
//             destination.setTemperature(25.0); // Default temperature
//         }
//         return destinationRepository.save(destination);
//     }
    

//     // Update an existing destination
//     public Destination updateDestination(String id, Destination updatedDestination) {
//         Optional<Destination> existingDestination = destinationRepository.findById(id);
//         if (existingDestination.isPresent()) {
//             updatedDestination.setId(id);
//             return destinationRepository.save(updatedDestination);
//         }
//         return null; // Or throw an exception if needed
//     }

//     // Delete a destination
//     public void deleteDestination(String id) {
//         destinationRepository.deleteById(id);
//     }

//     public List<Destination> getDestinationsByCategory(String category) {
//         return destinationRepository.findByCategory(category);
//     }

//     public List<Destination> getDestinations() {
//         return destinationRepository.findAll();  // or any other logic to fetch data
//     }
// }






// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.data.mongodb.core.query.Criteria;
// import org.springframework.data.mongodb.core.query.Query;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.GetMapping;

// import com.visitor.petitefrance.exception.DestinationNotFoundException;
// import com.visitor.petitefrance.model.Destination;
// import com.visitor.petitefrance.repository.DestinationRepository;

// @Service
// public class DestinationService {

//     @Autowired
//     private DestinationRepository destinationRepository;
//     public List<Destination> getAllDestinations() {
//         return destinationRepository.findAll(); // Ensure this is fetching correctly
//     }

//     @Autowired
//     private MongoTemplate mongoTemplate; // Inject MongoTemplate

//     // Get distinct categories by state using MongoTemplate
//     public List<String> getCategoriesByState(String state) {
//         Query query = new Query();
//         query.addCriteria(Criteria.where("state").is(state));
//         return mongoTemplate.findDistinct(query, "category", "destination", String.class);
//     }

//     // Get destinations by state and category
//     public List<Destination> getDestinationsByStateAndCategory(String state, String category) {
//         List<Destination> destinations = destinationRepository.findByStateAndCategory(state, category);
//         if (destinations.isEmpty()) {
//             throw new DestinationNotFoundException(
//                 "No destinations found for state: " + state + " and category: " + category
//             );
//         }
//         return destinations;
//     }

//     // Get a destination by ID
//     public Destination getDestinationById(String id) {
//         return destinationRepository.findById(id)
//             .orElseThrow(() -> new DestinationNotFoundException("Destination with ID " + id + " not found."));
//     }

//     // Create a new destination
//     public Destination createDestination(Destination destination) {
//         if (destination.getName() == null || destination.getName().isEmpty()) {
//             throw new IllegalArgumentException("Destination name cannot be null or empty.");
//         }
//         System.out.println("Saving destination: " + destination);
//         return destinationRepository.save(destination);
//     }

//     // Update an existing destination
//     public Destination updateDestination(String id, Destination destinationDetails) {
//         Destination existingDestination = destinationRepository.findById(id)
//             .orElseThrow(() -> new DestinationNotFoundException("Destination with ID " + id + " not found."));

//         // Update non-null properties
//         if (destinationDetails.getName() != null) 
//             existingDestination.setName(destinationDetails.getName());
//         if (destinationDetails.getDescription() != null) 
//             existingDestination.setDescription(destinationDetails.getDescription());
//         if (destinationDetails.getState() != null) 
//             existingDestination.setState(destinationDetails.getState());
//         if (destinationDetails.getCategory() != null) 
//             existingDestination.setCategory(destinationDetails.getCategory());
//         if (destinationDetails.getImageUrl() != null) 
//             existingDestination.setImageUrl(destinationDetails.getImageUrl());

//         return destinationRepository.save(existingDestination);
//     }

//     @GetMapping("/test-mongo")
//     public ResponseEntity<String> testMongoConnection() {
//     long count = destinationRepository.count(); // Count the documents
//     return ResponseEntity.ok("MongoDB connection is working. Destination count: " + count);
// }


//     // Delete a destination by its ID
//     public void deleteDestination(String id) {
//         if (!destinationRepository.existsById(id)) {
//             throw new DestinationNotFoundException("Destination with ID " + id + " does not exist.");
//         }
//         destinationRepository.deleteById(id);
//     }
// }
