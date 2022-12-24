package com.example.cityHotel.service;

import com.example.cityHotel.exception.*;
import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.repository.CityRepo;
import com.example.cityHotel.repository.HotelRepo;
import com.example.cityHotel.utility.CalculateDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private RatingService ratingService;


    public Hotel getHotel(Integer id)
    {
        Hotel hotel = this.hotelRepo.findById(id).orElseThrow(()->new HotelNotFoundException("Hotel with id: "+id+" not found"));
        return hotel;

    }
    @Transactional
    public Hotel saveHotel(Hotel hotel) {
        City city = cityRepo.findById(hotel.getCity().getId()).orElse(null);
        if(city==null) throw new CityNotFoundException("city is requires");
        hotel.setCity(city);

     city.getHotels().add(hotel);
       //Save the city to update its list of hotels
      cityRepo.save(city);


      // save the hotel
        if(hotel.getName()==null) throw new MissingHotelNameException("hotel name is required");
        if(hotel.getLocation()==null)
            throw new MissingLocationException("location attribute is required");
       return  hotelRepo.save( hotel);


    }
    public List<Hotel> getHotelsInCity(City city) {
        return hotelRepo.findByCity(city);
    }
    @Transactional
    public void delete(Integer id)
    {
        this.hotelRepo.deleteById(id);
    }
    @Transactional
    public Hotel update(Hotel hotel)
    {
        if(hotel.getName()==null) throw new MissingHotelNameException("hotel name is required");
        if(hotel.getLocation()==null)
            throw new MissingLocationException("location attribute is required");
        if(hotel.getCity()==null) throw new CityNotFoundException("city is required");
        return this.hotelRepo.save(hotel);
    }

    public List<Hotel> getAll() {
        return this.hotelRepo.findAll();
    }

    @Transactional
    public void rateHotel(Hotel hotel, int rating) {
        ratingService.rateHotel(hotel, rating);
    }

    public Hotel searchHotel(String name)
    {
        return this.hotelRepo.findByName(name);
    }
}
