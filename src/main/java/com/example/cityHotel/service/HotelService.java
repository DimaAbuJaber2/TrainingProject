package com.example.cityHotel.service;

import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.repository.CityRepo;
import com.example.cityHotel.repository.HotelRepo;
import com.example.cityHotel.utilities.CalculateDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private RatingService ratingService;


    public double getDistance(Hotel hotel) {

        return CalculateDistance.distance(hotel.getCity().getLocation().getLatitude(), hotel.getCity().getLocation().getLongitude(), hotel.getLocation().getLatitude(), hotel.getLocation().getLongitude());

    }
    public Hotel getHotel(Integer id)
    {
        Optional<Hotel> hotelOpt = this.hotelRepo.findById(id);
        return hotelOpt.orElse(null);

    }
    public Hotel saveHotel(Hotel hotel) {
        City city = cityRepo.findById(hotel.getCity().getId()).orElse(null);

        hotel.setCity(city);

     city.getHotels().add(hotel);
       //Save the city to update its list of hotels
      cityRepo.save(city);


      // //update the rate of hotel and Save
       return  hotelRepo.save( hotel);


    }
    public List<Hotel> getHotelsInCity(City city) {
        return hotelRepo.findByCity(city);
    }
    public void delete(Integer id)
    {
        this.hotelRepo.deleteById(id);
    }
    public Hotel update(Hotel hotel)
    {
        return this.hotelRepo.save(hotel);
    }

    public List<Hotel> getAll() {
        return this.hotelRepo.findAll();
    }

    public void rateHotel(Hotel hotel, int rating) {
        ratingService.rateHotel(hotel, rating);
    }
}
