package com.example.cityHotel.repository;

import com.example.cityHotel.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Integer> {
}
