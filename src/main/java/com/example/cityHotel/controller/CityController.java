package com.example.cityHotel.controller;
import com.example.cityHotel.dto.HotelDistanceDTO;
import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.service.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;


    @ApiOperation(value = "Get a city by its ID", response = City.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the city"),
            @ApiResponse(code = 404, message = "The city with the given ID was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable Integer id) {
        City city = cityService.getCity(id);
        return ResponseEntity.ok(city);
    }

    @ApiOperation(value = "Get All cities", response = City.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the city"),
            @ApiResponse(code = 404, message = "The city with the given ID was not found")
    })
    @GetMapping("/")
    public ResponseEntity<Page<City>> getCities(@RequestParam(name = "page",defaultValue = "0")int page,
                                                @RequestParam(name="size", defaultValue = "5") int size,
                                                @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
                                                @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        Sort.Direction sortDirection = Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.ASC);
        Pageable pageable= PageRequest.of(page,size, sortDirection, sortBy);
        Page<City> cities = cityService.getAll(pageable);
        return ResponseEntity.ok(cities);
    }

    @ApiOperation(value = "Create a new city", response = City.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the city"),
            @ApiResponse(code = 400, message = "Invalid request body")
    })
    @PostMapping("/")
    public ResponseEntity<City> save(@Valid @RequestBody City city) {
        City savedCity = cityService.save(city);
        return ResponseEntity.ok()
                .body(savedCity);
    }


    @ApiOperation(value = "Update an existing city", response = City.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the city"),
            @ApiResponse(code = 400, message = "Invalid request body"),
            @ApiResponse(code = 404, message = "The city with the given ID was not found")
    })

    @PutMapping("/")
    public ResponseEntity<City> updateCity( @Valid @RequestBody City city) {
        City updatedCity = cityService.updateCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCity);
    }


    @ApiOperation(value = "Delete a city by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted the city"),
            @ApiResponse(code = 404, message = "The city with the given ID was not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        cityService.delete(id);
        return ResponseEntity.ok().build();
    }




    @ApiOperation(value = "Get a list of hotels in a given city", response = HotelDistanceDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of hotels"),
            @ApiResponse(code = 404, message = "The city with the given ID was not found")
    })
    @GetMapping("/{id}/hotels")
    public ResponseEntity<Page<HotelDistanceDTO>> getHotels(@PathVariable int id, Pageable pageable) {
        City city = cityService.getCity(id);
        Page<HotelDistanceDTO> hotels = cityService.getAllHotels(city, pageable);
        return ResponseEntity.ok(hotels);
    }



    @ApiOperation(value = "Search for a city by its name", response = City.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the city"),
            @ApiResponse(code = 404, message = "The city with the given name was not found")
    })

    @GetMapping("/get/{name}")
    public ResponseEntity<City> search(@PathVariable String name) {
        City city= cityService.searchCity(name);
        return ResponseEntity.ok(city);
    }
}
