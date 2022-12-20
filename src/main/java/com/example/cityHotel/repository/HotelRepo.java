package com.example.cityHotel.repository;


import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo  extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByCity(City city);

    List<Hotel> findByIdIn(List<Integer> hotelIds);
}
