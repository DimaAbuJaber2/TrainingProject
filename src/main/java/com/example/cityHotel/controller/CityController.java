package com.example.cityHotel.controller;

import com.example.cityHotel.model.City;
import com.example.cityHotel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;
    @GetMapping("/get-city")
    public City getCity(@RequestParam Integer id) {return cityService.getCity(id);}
    @PostMapping("/save-city")
    public City save(@RequestBody City city)
    {
        return cityService.save(city);
    }

    @PutMapping("/update-city")
    public City update(@RequestBody City city)
    {
        return cityService.save(city);
    }
    @DeleteMapping("/delete-city")
    public void delete(@RequestParam Integer id) {cityService.delete(id);}

    @GetMapping("/get-all")
    public List<City> getAll() {return cityService.getAll();}

//    @GetMapping("/search-city")
//    public City search(@RequestParam String name) {return cityService.searchCity(name);}
}
