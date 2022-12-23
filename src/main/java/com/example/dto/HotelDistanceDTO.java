package com.example.dto;

import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;

public class HotelDistanceDTO {
    private int id;
    private String name;
    private City city;
    private double distance;
    private int numRatings;
    private double rating;

    public HotelDistanceDTO(Hotel hotel, double distance) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.city = hotel.getCity();
        this.distance = distance;
        this.numRatings = hotel.getNumRatings();
        this.rating = hotel.getRating();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
