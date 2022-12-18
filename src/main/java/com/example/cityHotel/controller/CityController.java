package com.example.cityHotel.controller;

import com.example.cityHotel.model.City;
import com.example.cityHotel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;
    @GetMapping("/get-city")
    public City getCity(@RequestParam Integer id)
    {
        return cityService.getCity(id);
    }
    @PostMapping("/save-city")
    public City getCity(@RequestBody City city)
    {
        return cityService.save(city);
    }

}
