package com.example.cityHotel.dto;

import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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



}
