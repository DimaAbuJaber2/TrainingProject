package com.example.cityHotel.service;

import com.example.cityHotel.model.City;
import com.example.cityHotel.repository.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepo cityRepo;
    public City getCity(Integer id)
    {
        Optional<City> city=this.cityRepo.findById(id);
        return city.orElse(null);
    }
    public City save(City city)
    {
       return this.cityRepo.save(city);
    }
}
