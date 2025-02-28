package com.visitor.petitefrance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visitor.petitefrance.model.Destination;
import com.visitor.petitefrance.model.District;
import com.visitor.petitefrance.service.DistrictService;

@RestController
@RequestMapping("/districts")
@CrossOrigin(origins = "http://localhost:3000/")
public class DistrictController {

    @Autowired
    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    // Endpoint to get all districts
    @GetMapping("/all")
    public ResponseEntity<List<District>> getAllDistricts() {
        List<District> districts = districtService.getAllDistricts();
        return ResponseEntity.ok(districts);  // Return the list of all districts, including embedded destinations
    }

    // Endpoint to get all districts without response entity
    @GetMapping
    public List<District> getDistricts() {
        return districtService.findAllDistricts();
    }

    // Endpoint to get a specific district by name
    @GetMapping("/{name:[a-zA-Z0-9_-]+}")
    public ResponseEntity<District> getDistrictByName(@PathVariable String name) {
        // Convert the name to lowercase to ensure the comparison is case-insensitive
        return districtService.getDistrictByName(name.toLowerCase())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{districtId}/addDestination")
    public ResponseEntity<String> addDestination(
            @PathVariable String districtId,
            @RequestBody Destination newDestination) {
        boolean isAdded = districtService.addDestinationToDistrict(districtId, newDestination);
        if (isAdded) {
            return ResponseEntity.ok("Destination added successfully!");
        } else {
            return ResponseEntity.badRequest().body("Failed to add destination.");
        }
    }
}


// import java.util.ArrayList;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.visitor.petitefrance.model.Destination;
// import com.visitor.petitefrance.model.District;
// import com.visitor.petitefrance.repository.DistrictRepository;

// @RestController
// @RequestMapping("/api/districts")
// @CrossOrigin(origins = "http://localhost:3000") // Adjust this based on your React app's origin

// public class DistrictController {

//     @Autowired
//     private DistrictRepository districtRepository;

//     // Fetch district details by name
//     @GetMapping("/{name}")
//     public ResponseEntity<District> getDistrictByName(@PathVariable String name) {
//         District district = districtRepository.findByNameIgnoreCase(name);
//         if (district == null) {
//             return ResponseEntity.notFound().build();
//         }
//         return ResponseEntity.ok(district);
//     }

//     // Add a new destination to a district
//     @PostMapping("/{name}/destinations")
//     public ResponseEntity<String> addDestinationToDistrict(
//             @PathVariable String name, @RequestBody Destination newDestination) {

//         District district = districtRepository.findByNameIgnoreCase(name);
//         if (district == null) {
//             return ResponseEntity.notFound().build();
//         }

//         if (district.getDestinations() == null) {
//             district.setDestinations(new ArrayList<>());
//         }
//         district.getDestinations().add(newDestination);
//         districtRepository.save(district);

//         return ResponseEntity.ok("Destination added successfully!");
//     }
// }

