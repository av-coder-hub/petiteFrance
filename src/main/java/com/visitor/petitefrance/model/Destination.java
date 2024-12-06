package com.visitor.petitefrance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Document(collection = "destinations") // Maps this class to the "destinations" collection
public class Destination {
    @Id
    private String id; // Use String to represent MongoDB's ObjectId

    @NotBlank(message = "district is required")
    private String district;

    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 100, message = "Location must be at most 100 characters")
    private String location;

    @Size(max = 50, message = "Temperature information must be at most 50 characters")
    private Double temperature;

    @Size(max = 100, message = "Tagline must be at most 100 characters")
    private String tagline;

    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format")
    private String imageUrl;

    @NotBlank(message = "Category is required")
    @Pattern(regexp = "^(NATURE|RELIGIOUS|HERITAGE)$", message = "Category must be one of: NATURE, RELIGIOUS, HERITAGE")
    private String category;

    // Default no-args constructor (required by frameworks like Spring)
    public Destination() {}

    // All-arguments constructor (for easier instantiation)
    public Destination(String district, String name, String location, Double temperature, String tagline, String description, String imageUrl, String category) {
        this.district = district;
        this.name = name;
        this.location = location;
        this.temperature = temperature;
        this.tagline = tagline;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;        
    }

    public Destination(String district, String name, String imageUrl) {
        this.district = district;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
