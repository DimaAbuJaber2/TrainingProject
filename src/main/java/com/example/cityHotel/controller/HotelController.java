package com.example.cityHotel.controller;


import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.dto.RatingRequest;
import com.example.cityHotel.service.CityService;
import com.example.cityHotel.service.HotelService;
import com.example.cityHotel.service.RatingService;
import com.example.cityHotel.dto.HotelDistanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private CityService cityService;

    @Autowired
    private RatingService ratingService;


@GetMapping("/{id}")
public HotelDistanceDTO getHotel(@PathVariable Integer id) {
    Hotel hotel = hotelService.getHotel(id);
    double distance = hotel.findDistanceFromCity(hotel.getCity()).getDistance();
    return new HotelDistanceDTO(hotel, distance);
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
    public List<HotelDistanceDTO> getAll() {List<Hotel> hotels=hotelService.getAll();
        List<HotelDistanceDTO> hotelDistanceDTOs = new ArrayList<>();
        for(Hotel hotel: hotels)
        {
            double distance=hotel.findDistanceFromCity(hotel.getCity()).getDistance();
            HotelDistanceDTO dto=new HotelDistanceDTO(hotel,distance);
            hotelDistanceDTOs.add(dto);
        }

        return hotelDistanceDTOs;
}

    @GetMapping("/city/{cityId}")
    public List<HotelDistanceDTO> getHotelsInCity(@PathVariable Integer cityId) {
        City city = cityService.getCity(cityId);
        List<Hotel> hotels = hotelService.getHotelsInCity(city);
        List<HotelDistanceDTO> hotelDistanceDTOs = new ArrayList<>();
        for(Hotel hotel: hotels)
        {
            double distance=hotel.findDistanceFromCity(city).getDistance();
            HotelDistanceDTO dto=new HotelDistanceDTO(hotel,distance);
            hotelDistanceDTOs.add(dto);
        }

        return hotelDistanceDTOs.stream()
                .sorted(Comparator.comparingDouble(HotelDistanceDTO:: getDistance))
                .collect(Collectors.toList());
    }


    @PostMapping("/{id}/rate")
    public ResponseEntity<Void> rateHotel(@PathVariable int id, @RequestBody RatingRequest ratingRequest) {
        Hotel hotel=hotelService.getHotel(id);
        hotelService.rateHotel(hotel, ratingRequest.getRating());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{name}")
    public HotelDistanceDTO search(@PathVariable String name) {Hotel hotel= hotelService.searchHotel(name);
        double distance = hotel.findDistanceFromCity(hotel.getCity()).getDistance();
        return new HotelDistanceDTO(hotel, distance);}
}
