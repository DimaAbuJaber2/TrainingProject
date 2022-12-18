package com.example.cityHotel.controller;

import com.example.cityHotel.model.City;
import com.example.cityHotel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class CityController {
    @Autowired
    private CityService cityService;
    @GetMapping("/get-city")
    public City getCity(@RequestParam String name)
    {
        return cityService.getCity(name);
    }

}
