package com.example.cityHotel.repository;

import com.example.cityHotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo  extends JpaRepository<Hotel, Integer> {
}
