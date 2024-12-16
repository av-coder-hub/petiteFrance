package com.visitor.petitefrance.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Embeddable
public class Destination {
   
    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 100, message = "Location must be at most 100 characters")
    private String location;

    @Min(value = -50, message = "Temperature cannot be less than -50°C")
    @Max(value = 60, message = "Temperature cannot exceed 60°C")
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
    public Destination(String name, String location, Double temperature, String tagline, String description, String imageUrl, String category) {
        this.name = name;
        this.location = location;
        this.temperature = temperature;
        this.tagline = tagline;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;        
    }

    public Destination(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
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
