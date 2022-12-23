package com.example.cityHotel.controller;
import com.example.cityHotel.model.City;
import com.example.cityHotel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/get/{name}")
    public City search(@PathVariable String name) {return cityService.searchCity(name);}
}
