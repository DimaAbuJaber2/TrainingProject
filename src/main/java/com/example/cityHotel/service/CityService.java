package com.example.cityHotel.service;

import com.example.cityHotel.exception.CityNotFoundException;
import com.example.cityHotel.exception.MissingCityNameException;
import com.example.cityHotel.exception.MissingLocationException;
import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.repository.CityRepo;
import com.example.cityHotel.repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CityService {

    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private HotelRepo hotelRepo;

    public City getCity(Integer id)
    {
       City city=this.cityRepo.findById(id).orElseThrow(()->new CityNotFoundException("city with id: "+id+" not found"));
        return city;

    }
    public City save(City city)
    {
        if(city.getName()==null) throw new MissingCityNameException("city name is required");
        if(city.getLocation()==null)
            throw new MissingLocationException("location attribute is required");
        return this.cityRepo.save(city);
    }
    public void delete(Integer id)
    {
         this.cityRepo.deleteById(id);
    }

    public City updateCity(City city)
    {
        if(city.getName()==null) throw new MissingCityNameException("city name is required");
        if(city.getLocation()==null)
            throw new MissingLocationException("location attribute is required");
        return cityRepo.save(city);
    }


    public List<City> getAll() {
        return this.cityRepo.findAll();
    }

    public City searchCity(String name)
    {
       return this.cityRepo.findByName(name);
    }

}
