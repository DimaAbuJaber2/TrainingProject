package com.example.cityHotel.repository;


import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo  extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByCity(City city);
    @Query("SELECT h FROM Hotel h WHERE h.name = :name")
    Hotel findByName(@Param("name") String name);
}
