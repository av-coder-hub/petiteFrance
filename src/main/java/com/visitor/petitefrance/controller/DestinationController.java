package com.visitor.petitefrance.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.visitor.petitefrance.model.Destination;
import com.visitor.petitefrance.service.DestinationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    // Endpoint to fetch all destinations
    @GetMapping
    public List<Destination> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    // Get categories by state (e.g., Nature, Culture, Adventure)
    @GetMapping("/categories/{state}")
    public List<String> getCategoriesByState(@PathVariable String state) {
        return destinationService.getCategoriesByState(state);
    }

    // Get destinations by state and category
    @GetMapping("/{state}/{category}")
    public List<Destination> getDestinationsByStateAndCategory(@PathVariable String state, @PathVariable String category) {
        return destinationService.getDestinationsByStateAndCategory(state, category);
    }

    // Endpoint to get a destination by ID
    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable Long id) {
        Optional<Destination> destination = destinationService.getDestinationById(id);
        return destination.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to create a new destination with validation
    @PostMapping
    public ResponseEntity<?> createDestination(@Valid @RequestBody Destination destination, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect validation errors and return a response
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        destinationService.createDestination(destination);
        return ResponseEntity.status(201).body(destination);
    }

    // Endpoint to create a destination with image upload
    @PostMapping("/with-image")
    public ResponseEntity<?> createDestinationWithImage(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("state") String state,
            @RequestParam("category") String category,
            @RequestParam("image") MultipartFile image) {

        // Validate the inputs (you can expand validation as needed)
        if (name.isEmpty() || description.isEmpty() || state.isEmpty() || category.isEmpty()) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        try {
            // Save the image and get the image URL (path or cloud URL)
            String imageUrl = saveImage(image);

            // Create a new Destination object and set the fields
            Destination destination = new Destination();
            destination.setName(name);
            destination.setDescription(description);
            destination.setState(state);
            destination.setCategory(category);
            destination.setImageUrl(imageUrl); // Set the image URL after uploading

            // Save the destination
            destinationService.createDestination(destination);

            // Return the saved destination with HTTP status 201 (Created)
            return ResponseEntity.status(201).body(destination);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error while processing the image");
        }
    }

    // Helper method to save the image and return the image URL
    private String saveImage(MultipartFile image) throws IOException {
        // Specify the path to save the image (you can adjust the path or use cloud storage)
        String imagePath = "uploads/" + image.getOriginalFilename();

        // Create a File object and save the image
        File file = new File(imagePath);
        image.transferTo(file);

        // Return the image URL or path (you can adjust this to serve from a URL or use cloud storage)
        return imagePath;
    }

    // Endpoint to update an existing destination with validation
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDestination(@PathVariable Long id, 
                                               @Valid @RequestBody Destination destinationDetails, 
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Return validation errors
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        try {
            Destination updatedDestination = destinationService.updateDestination(id, destinationDetails);
            return ResponseEntity.ok(updatedDestination);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete a destination by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Long id) {
        try {
            destinationService.deleteDestination(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
