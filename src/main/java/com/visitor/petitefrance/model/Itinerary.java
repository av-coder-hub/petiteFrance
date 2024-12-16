package com.visitor.petitefrance.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "itineraries")
public class Itinerary {
    @Id
    private String id;
    private String userId;  // Reference to the user creating the itinerary
    private String tripName; // Optional name for the trip
    private String startDate;
    private String endDate;
    private List<DayPlan> days; // List of day-wise plans
    private int points; // Total points earned

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<DayPlan> getDays() {
        return days;
    }

    public void setDays(List<DayPlan> days) {
        this.days = days;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
