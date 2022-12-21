package com.example.cityHotel.service;

import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    private HotelRepo hotelRepo;

    public void rateHotel(Hotel hotel, double rating) {
        double currentRating = hotel.getRating();
        int numRatings = hotel.getNumRatings();
        double newRating = (currentRating * numRatings + rating) / (numRatings + 1);
        hotel.setRating(newRating);
        hotel.setNumRatings(numRatings + 1);
        hotelRepo.save(hotel);
    }
}
