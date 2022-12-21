package com.example.cityHotel.controller;


import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.service.CityService;
import com.example.cityHotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private CityService cityService;
//    @GetMapping("/{id}")
//    public Hotel getHotel(@PathVariable Integer id) {
//        return hotelService.getHotel(id);
//    }
@GetMapping("/{id}")
public ResponseEntity<Map<String, Object>> getHotel(@PathVariable Integer id) {
    Hotel hotel = hotelService.getHotel(id);
    if (hotel != null) {
        double distance = hotelService.getDistance(hotel);
        Map<String, Object> response = new HashMap<>();
        response.put("hotel", hotel);
        response.put("distance", distance);
        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.notFound().build();
    }
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
