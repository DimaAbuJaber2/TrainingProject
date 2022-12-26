package com.example.cityHotel.controller;


import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.service.CityService;
import com.example.cityHotel.service.HotelService;
import com.example.cityHotel.service.RatingService;
import com.example.cityHotel.dto.HotelDistanceDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @ApiOperation(value = "Get a Hotel with it's distance by it's ID", response = HotelDistanceDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the Hotel"),
            @ApiResponse(code = 404, message = "The Hotel with the given ID was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HotelDistanceDTO> getHotel(@PathVariable Integer id) {
        Hotel hotel = hotelService.getHotel(id);
        double distance = hotel.findDistanceFromCity(hotel.getCity()).getDistance();
        HotelDistanceDTO hotelDistanceDTO = new HotelDistanceDTO(hotel, distance);
        return ResponseEntity.ok(hotelDistanceDTO);
    }


    @ApiOperation(value = "Create a new hotel", response = Hotel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the hotel"),
            @ApiResponse(code = 400, message = "Invalid request body")
    })
    @PostMapping("/")
    public ResponseEntity<Hotel> saveHotel(@Valid @RequestBody Hotel hotel) {
        Hotel savedHotel = hotelService.saveHotel(hotel);
        return ResponseEntity.ok(savedHotel);
    }


    @ApiOperation(value = "Update an existing hotel", response = Hotel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the hotel"),
            @ApiResponse(code = 400, message = "Invalid request body"),
            @ApiResponse(code = 404, message = "The hotel with the given ID was not found")
    })
    @PutMapping("/")
    public ResponseEntity<Hotel> update(@Valid @RequestBody Hotel hotel) {
        Hotel updatedHotel = hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedHotel);
    }


    @ApiOperation(value = "Delete a hotel by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted the hotel"),
            @ApiResponse(code = 404, message = "The hotel with the given ID was not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        hotelService.delete(id);
        return ResponseEntity.ok().build();
    }




    @ApiOperation(value = "Get a list of all hotels with their distances", response = HotelDistanceDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of hotels with the distance")
    })
    @GetMapping("/")
    public ResponseEntity<List<HotelDistanceDTO>> getAll() {
        List<Hotel> hotels = hotelService.getAll();
        List<HotelDistanceDTO> hotelDistanceDTOs = new ArrayList<>();
        for (Hotel hotel : hotels) {
            double distance = hotel.findDistanceFromCity(hotel.getCity()).getDistance();
            HotelDistanceDTO dto = new HotelDistanceDTO(hotel, distance);
            hotelDistanceDTOs.add(dto);
        }

        return ResponseEntity.ok(hotelDistanceDTOs);
    }


    @ApiOperation(value = "Get a list of hotels in a given city, sorted by distance from the city", response = HotelDistanceDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of hotels"),
            @ApiResponse(code = 404, message = "The city with the given ID was not found")
    })

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<HotelDistanceDTO>> getHotelsInCity(@PathVariable Integer cityId) {
        City city = cityService.getCity(cityId);
        List<Hotel> hotels = hotelService.getHotelsInCity(city);
        List<HotelDistanceDTO> hotelDistanceDTOs = new ArrayList<>();
        for (Hotel hotel : hotels) {
            double distance = hotel.findDistanceFromCity(city).getDistance();
            HotelDistanceDTO dto = new HotelDistanceDTO(hotel, distance);
            hotelDistanceDTOs.add(dto);
        }

        List<HotelDistanceDTO> sortedHotelDistanceDTOs = hotelDistanceDTOs.stream()
                .sorted(Comparator.comparingDouble(HotelDistanceDTO::getDistance))
                .collect(Collectors.toList());

        return ResponseEntity.ok(sortedHotelDistanceDTOs);
    }



    @ApiOperation(value = "Rate a hotel")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully rated the hotel"),
            @ApiResponse(code = 400, message = "Invalid request body"),
            @ApiResponse(code = 404, message = "The hotel with the given ID was not found")
    })
    @PostMapping("/{id}/rate")
    public ResponseEntity<Void> rateHotel(@PathVariable int id,@Valid @RequestBody RatingRequest ratingRequest) {
        Hotel hotel=hotelService.getHotel(id);
        hotelService.rateHotel(hotel, ratingRequest.getRating());

        return ResponseEntity.noContent().build();
    }




    @ApiOperation(value = "get the hotel by name",response = HotelDistanceDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the hotel"),
            @ApiResponse(code = 404, message = "The hotel with the given name was not found")
    })
    @GetMapping("/get/{name}")
    public ResponseEntity<HotelDistanceDTO> search(@PathVariable String name) {
        Hotel hotel = hotelService.searchHotel(name);
        double distance = hotel.findDistanceFromCity(hotel.getCity()).getDistance();
        HotelDistanceDTO hotelDistanceDTO = new HotelDistanceDTO(hotel, distance);
        return ResponseEntity.ok(hotelDistanceDTO);
    }
}
