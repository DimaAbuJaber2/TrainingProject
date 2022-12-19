package com.example.cityHotel.controller;


import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @GetMapping("/get-hotel")
    public Hotel getHotel(@RequestParam Integer id) {return hotelService.getHotel(id);}
    @PostMapping("/save-hotel")
    public Hotel save(@RequestBody Hotel hotel)
    {
        return hotelService.save(hotel);
    }

    @PutMapping("/update-hotel")
    public Hotel update(@RequestBody Hotel hotel)
    {
        return hotelService.save(hotel);
    }
    @DeleteMapping("/delete-hotel")
    public void delete(@RequestParam Integer id) {hotelService.delete(id);}

    @GetMapping("/get-all-hotels")
    public List<Hotel> getAll() {return hotelService.getAll();}
}
