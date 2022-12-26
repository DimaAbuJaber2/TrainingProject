package com.example.cityHotel.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import com.example.cityHotel.repository.CityRepo;
import com.example.cityHotel.repository.HotelRepo;
import lombok.NonNull;
import net.bytebuddy.build.Plugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.example.cityHotel.dto.DistanceDTO;
import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.model.locations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Priority;

@SpringBootTest
class CityHotelApplicationTests {

    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private HotelRepo hotelRepo;

    @Test
    @Order(1)

        public void testCreateCityAndHotel() {

       City city1=new City();
        city1.setName("Jenin");
        city1.setAddress("west bank");
        city1.setLocation(new locations(93.5,40.08));
       Hotel hotel1=new Hotel();
        hotel1.setName("jenin-hotel");
        hotel1.setLocation(new locations(30.87,40.66));
        hotel1.setCity(city1);


                cityRepo.save(city1);
                hotelRepo.save(hotel1);
                assertNotNull(cityRepo.findById(city1.getId()).get());
                assertNotNull(hotelRepo.findById(hotel1.getId()).get());


}


    @Test
    @Order(2)
    public void testUpdateCityAndHotel() {

       City city2=new City();
        city2.setName("Nablus");
        city2.setAddress("west bank");
        city2.setLocation(new locations(95.5,42.08));
       Hotel  hotel2=new Hotel();
        hotel2.setName("Nablus-hotel");
        hotel2.setLocation(new locations(77.87,76.66));
        hotel2.setCity(city2);


        cityRepo.save(city2);
        hotelRepo.save(hotel2);
        assertNotNull(cityRepo.findById(city2.getId()).get());
        assertNotNull(hotelRepo.findById(hotel2.getId()).get());

        city2.setName("Ramallah");
        cityRepo.save(city2);
        hotel2.setName("Ramallah-hotel");
        hotelRepo.save(hotel2);
        assertEquals(city2.getName(),cityRepo.findById(city2.getId()).get().getName());
        assertEquals(hotel2.getName(),hotelRepo.findById(hotel2.getId()).get().getName());


    }

    @Test
    @Order(3)
    public void testDeleteCityAndHotel()
    {
        City city1=new City();
        city1.setName("New York");
        city1.setAddress("----");
        city1.setLocation(new locations(34.5,76.08));
        Hotel  hotel1=new Hotel();
        hotel1.setName("New-hotel");
        hotel1.setLocation(new locations(87.87,84.66));
        hotel1.setCity(city1);


        cityRepo.save(city1);
        hotelRepo.save(hotel1);
        assertNotNull(cityRepo.findById(city1.getId()).get());
        assertNotNull(hotelRepo.findById(hotel1.getId()).get());


        hotelRepo.deleteById(hotel1.getId());
        assertFalse(hotelRepo.existsById(hotel1.getId()));
        cityRepo.deleteById(city1.getId());

        assertFalse(cityRepo.existsById(city1.getId()));


    }


//    @Test
//    public void testGetDistanceBetweenCities() {
//        City city1=new City();
//        city1.setName("CityDistance");
//        city1.setAddress("----");
//        city1.setLocation(new locations(60.5,87.08));
//        Hotel  hotel1=new Hotel();
//        hotel1.setName("hotelDistance");
//        hotel1.setLocation(new locations(54.9,77.66));
//        hotel1.setCity(city1);
//
//
//        cityRepo.save(city1);
//        hotelRepo.save(hotel1);
//        assertNotNull(cityRepo.findById(city1.getId()).get());
//        assertNotNull(hotelRepo.findById(hotel1.getId()).get());
//
//       double distance= hotelRepo.findById(hotel1.getId()).get().findDistanceFromCity(cityRepo.findById(city1.getId()).get()).getDistance();
//       assertEquals( 858.9285438746626,distance);
//
//    }


}

