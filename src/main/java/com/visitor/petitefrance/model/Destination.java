package com.visitor.petitefrance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")      // Ensure that name is not empty
    private String name;            // Name of the destination
    private String tagline;         // Short tagline (Nature, Culture, Heritage, etc.)
    
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")  // Enforce description length
    private String description;     // Detailed description of the destination

    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format")  // Ensure imageUrl is a valid URL
    private String imageUrl;        // URL or path to an image of the destination

    @NotBlank(message = "State is required") // Ensure state is not empty
    private String state;          // New field for region (Puducherry, Karaikal, etc.)

    public enum Category {
        NATURE, RELIGIOUS, HERITAGE;
    }
    
    @Pattern(regexp = "^(NATURE|RELIGIOUS|HERITAGE)$", message = "Invalid category")
    private String category;
    
    private String location;        // Location or area name for the destination
    private String temperature;     // (Optional) Temperature or weather info

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
