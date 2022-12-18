package com.example.cityHotel.service;

import com.example.cityHotel.model.City;
import com.example.cityHotel.repository.CityRepo;

import java.util.Optional;

public class CityService {
    private CityRepo cityRepo;
    public City getCity(String name)
    {
        Optional<City> city=this.cityRepo.findById(name);
        return city.orElse(null);
    }
}
