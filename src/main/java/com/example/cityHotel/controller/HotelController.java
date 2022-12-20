package com.example.cityHotel.controller;


import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.service.CityService;
import com.example.cityHotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private CityService cityService;
    @GetMapping("/{id}")
    public Hotel getHotel(@PathVariable Integer id) {
        return hotelService.getHotel(id);
    }
    @PostMapping("/")

    public Hotel saveHotel(@RequestBody Hotel hotel) {
        return hotelService.saveHotel(hotel);
    }
    @PutMapping("/")
    public Hotel update(@RequestBody Hotel hotel)
    {
        return hotelService.saveHotel(hotel);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {hotelService.delete(id);}

    @GetMapping("/")
    public List<Hotel> getAll() {return hotelService.getAll();}

    @GetMapping("/city/{cityId}")
    public List<Hotel> getHotelsInCity(@PathVariable Integer cityId) {
        City city = cityService.getCity(cityId);
        List<Hotel> hotels = hotelService.getHotelsInCity(city);
        return hotels;

    }
}
