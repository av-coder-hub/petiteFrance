package com.visitor.petitefrance.model;

import java.util.List;

public class DayPlan {
    private int dayNumber;
    private String district; 
    private List<DestinationPlan> destinations; 

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<DestinationPlan> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationPlan> destinations) {
        this.destinations = destinations;
    }
}
