package com.example.cityHotel.service;

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
        Optional<City> city=this.cityRepo.findById(id);
        return city.orElse(null);
    }
    public City save(City city)
    {
       return this.cityRepo.save(city);
    }
    public void delete(Integer id)
    {
         this.cityRepo.deleteById(id);
    }
    public City updateCity( Integer id, List<Integer> hotelIds, City city) {
        City existingCity = cityRepo.findById(id).orElse(null);
        List<Hotel> hotels = hotelRepo.findByIdIn(hotelIds);

        existingCity.setName(city.getName());
        existingCity.setAddress(city.getAddress());
        existingCity.setLocation(city.getLocation());
        existingCity.setHotels(hotels);

        for (Hotel hotel : hotels) {
            hotel.setCity(existingCity);
        }

        cityRepo.save(existingCity);
        hotelRepo.saveAll(hotels);

        return existingCity;
    }


    public List<City> getAll() {
        return this.cityRepo.findAll();
    }

//    public City searchCity(String name)
//    {
//        Optional<City> city=this.cityRepo.search_city(name);
//        return city.orElse(null);
//    }

}
