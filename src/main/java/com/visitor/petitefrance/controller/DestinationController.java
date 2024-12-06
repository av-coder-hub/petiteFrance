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

import com.visitor.petitefrance.model.Destination;
import com.visitor.petitefrance.service.DestinationService;

@RestController
@RequestMapping("/api/destinations")
@CrossOrigin(origins = "http://localhost:3000") // Adjust this based on your React app's origin
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    // Get all destinations
    @GetMapping
    public List<Destination> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    @GetMapping("/{category}")
    public List<Destination> getDestinationsByCategory(@PathVariable String category) {
        return destinationService.getDestinationsByCategory(category);
    }

    // Add a new destination
    @PostMapping
    public Destination addDestination(@RequestBody Destination destination) {
        return destinationService.addDestination(destination);
    }

    

    // Update an existing destination
    @PutMapping("/{id}")
    public Destination updateDestination(@PathVariable String id, @RequestBody Destination destination) {
        return destinationService.updateDestination(id, destination);
    }

    // Delete a destination
    @DeleteMapping("/{id}")
    public void deleteDestination(@PathVariable String id) {
        destinationService.deleteDestination(id);
    }
}


// import java.io.IOException;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.visitor.petitefrance.exception.DestinationNotFoundException;
// import com.visitor.petitefrance.model.Destination;
// import com.visitor.petitefrance.service.DestinationService;
// import com.visitor.petitefrance.service.FileUploadService;

// import jakarta.validation.Valid;

// @RestController
// @CrossOrigin(origins = "http://localhost:3000")
// @RequestMapping("/api/destinations")
// public class DestinationController {

//     @Autowired
//     private DestinationService destinationService;

//     @Autowired
//     private FileUploadService fileUploadService;

//     // Endpoint to fetch all destinations
//     @GetMapping
//     public ResponseEntity<List<Destination>> getAllDestinations() {
//         List<Destination> destinations = destinationService.getAllDestinations();
//         return ResponseEntity.ok(destinations);
//     }

//     // Endpoint to fetch categories by state
//     @GetMapping("/categories/{state}")
//     public ResponseEntity<List<String>> getCategoriesByState(@PathVariable String state) {
//         List<String> categories = destinationService.getCategoriesByState(state);
//         return ResponseEntity.ok(categories);
//     }

//     // Endpoint to fetch destinations by state and category
//     @GetMapping("/{state}/{category}")
//     public ResponseEntity<?> getDestinationsByStateAndCategory(
//             @PathVariable String state,
//             @PathVariable String category) {
//         try {
//             List<Destination> destinations = destinationService.getDestinationsByStateAndCategory(state, category);
//             return ResponseEntity.ok(destinations);
//         } catch (DestinationNotFoundException e) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//         }
//     }

//     // Endpoint to fetch a destination by ID
//     @GetMapping("/{id}")
//     public ResponseEntity<?> getDestinationById(@PathVariable String id) {
//         try {
//             Destination destination = destinationService.getDestinationById(id);
//             return ResponseEntity.ok(destination);
//         } catch (DestinationNotFoundException e) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//         }
//     }

//     // Endpoint to create a new destination
//     @PostMapping
//     public ResponseEntity<?> createDestination(@Valid @RequestBody Destination destination, BindingResult bindingResult) {
//         if (bindingResult.hasErrors()) {
//             return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
//         }
//         Destination createdDestination = destinationService.createDestination(destination);
//         return ResponseEntity.status(HttpStatus.CREATED).body(createdDestination);
//     }

//     // Endpoint to create a destination with an image upload
//     @PostMapping("/with-image")
//     public ResponseEntity<?> createDestinationWithImage(
//             @RequestParam("name") String name,
//             @RequestParam("description") String description,
//             @RequestParam("state") String state,
//             @RequestParam("category") String category,
//             @RequestParam("image") MultipartFile image) {

//         if (name.isEmpty() || description.isEmpty() || state.isEmpty() || category.isEmpty()) {
//             return ResponseEntity.badRequest().body("All fields are required");
//         }

//         try {
//             // Use FileUploadService to save the file
//             String imageUrl = fileUploadService.saveFile(image, "uploads");

//             // Create the destination object
//             Destination destination = new Destination(name, description, state, category, imageUrl);

//             // Save the destination using the service
//             Destination createdDestination = destinationService.createDestination(destination);

//             // Return the saved object with status 201
//             return ResponseEntity.status(HttpStatus.CREATED).body(createdDestination);
//         } catch (IOException e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while processing the image");
//         }
//     }

//     @PostMapping("/add")
// public ResponseEntity<String> addDestination(@RequestBody Destination destination) {
//     try {
//         // Save destination to MongoDB
//         destinationService.createDestination(destination);
//         return ResponseEntity.status(HttpStatus.CREATED).body("Destination added successfully");
//     } catch (Exception e) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add destination: " + e.getMessage());
//     }
// }

//     // Endpoint to update an existing destination
//     @PutMapping("/{id}")
//     public ResponseEntity<?> updateDestination(@PathVariable String id,
//                                                @Valid @RequestBody Destination destinationDetails,
//                                                BindingResult bindingResult) {
//         if (bindingResult.hasErrors()) {
//             return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
//         }
//         try {
//             Destination updatedDestination = destinationService.updateDestination(id, destinationDetails);
//             return ResponseEntity.ok(updatedDestination);
//         } catch (DestinationNotFoundException e) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//         }
//     }

//     // Endpoint to delete a destination by ID
//     @DeleteMapping("/{id}")
//     public ResponseEntity<?> deleteDestination(@PathVariable String id) {
//         try {
//             destinationService.deleteDestination(id);
//             return ResponseEntity.noContent().build();
//         } catch (DestinationNotFoundException e) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//         }
//     }
// }
