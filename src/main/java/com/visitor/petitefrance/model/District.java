package com.visitor.petitefrance.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Embedded;

@Document(collection = "districts")
public class District {
    @Id
    private String id;
    private String name;
    private String bannerImage;
    private String tagline;
    private String aboutText;
    private String mapImage;


    @Embedded
    private List<Destination> destinations = new ArrayList<>(); 

    public District() {}

    public District(String name, String bannerImage, String tagline, String aboutText) {
        this.name = name;
        this.bannerImage = bannerImage;
        this.tagline = tagline;
        this.aboutText = aboutText;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
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

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getAboutText() {
        return aboutText;
    }

    public void setAboutText(String aboutText) {
        this.aboutText = aboutText;
    }

    public String getMapImage() {
        return mapImage;
    }

    public void setMapImage(String mapImage) {
        this.mapImage = mapImage;
    }

}
