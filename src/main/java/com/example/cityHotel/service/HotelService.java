package com.example.cityHotel.service;

import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepo hotelRepo;
    public Hotel getHotel(Integer id)
    {
        Optional<Hotel> hotel=this.hotelRepo.findById(id);
        return hotel.orElse(null);
    }
    public Hotel save(Hotel hotel)
    {
        return this.hotelRepo.save(hotel);
    }
    public void delete(Integer id)
    {
        this.hotelRepo.deleteById(id);
    }
    public Hotel update(Hotel hotel)
    {
        return this.hotelRepo.save(hotel);
    }

    public List<Hotel> getAll() {
        return this.hotelRepo.findAll();
    }
}
