package com.example.cityHotel.repository;

import com.example.cityHotel.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Integer> {

//    @Query("select c from City c where c.cityName LIKE %:cityName%")
//    Optional<City> search_city(@Param("cityName") String name);
}
