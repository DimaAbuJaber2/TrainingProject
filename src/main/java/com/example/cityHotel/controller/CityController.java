package com.example.cityHotel.controller;
import com.example.cityHotel.dto.HotelDistanceDTO;
import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.service.CityService;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;
    @GetMapping("/{id}")
    public City getCity(@PathVariable Integer id) {return cityService.getCity(id);}
    @PostMapping("/")
    public City save(@RequestBody City city)
    {
        return cityService.save(city);
    }

    @PutMapping("/")
    public City updateCity(@RequestBody City city) {
        return cityService.updateCity(city);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {cityService.delete(id);}

    @GetMapping("/")
    public List<City> getAll() {return cityService.getAll();}

    @GetMapping("/{id}/hotels")
    public List<HotelDistanceDTO> getAllHotels(@PathVariable Integer id) {
        City city= cityService.getCity(id);
        List<Hotel>hotels=city.getHotels();
        List<HotelDistanceDTO>hotelDistanceDTOS=new ArrayList<>();
        for(Hotel hotel:hotels)
        {
            double distance=hotel.findDistanceFromCity(city).getDistance();
             HotelDistanceDTO dto=new HotelDistanceDTO(hotel,distance);
             hotelDistanceDTOS.add(dto);
        }

        return hotelDistanceDTOS.stream()
                .sorted(Comparator.comparingDouble(HotelDistanceDTO:: getDistance))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/{name}")
    public City search(@PathVariable String name) {return cityService.searchCity(name);}
}
