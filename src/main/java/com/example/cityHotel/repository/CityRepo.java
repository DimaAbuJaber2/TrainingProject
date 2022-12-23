package com.example.cityHotel.repository;

import com.example.cityHotel.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepo extends JpaRepository<City, Integer> {

    @Query("SELECT c FROM City c WHERE c.name = :name")
    City findByName(@Param("name") String name);
}
