package com.example.cityHotel.service;

import com.example.cityHotel.dto.HotelDistanceDTO;
import com.example.cityHotel.exception.CityNotFoundException;
import com.example.cityHotel.exception.MissingCityNameException;
import com.example.cityHotel.exception.MissingLocationException;
import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.repository.CityRepo;
import com.example.cityHotel.repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class CityService {

    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private HotelRepo hotelRepo;

    public City getCity(Integer id) {
        City city = this.cityRepo.findById(id).orElseThrow(() -> new CityNotFoundException("city with id: " + id + " not found"));
        return city;

    }

    @Transactional
    public City save(City city) {
        if (city.getName() == null) throw new MissingCityNameException("city name is required");
        if (city.getLocation() == null)
            throw new MissingLocationException("location attribute is required");
        return this.cityRepo.save(city);
    }

    @Transactional
    public void delete(Integer id) {
        this.cityRepo.deleteById(id);
    }

    @Transactional
    public City updateCity(City city) {
        if (city.getName() == null) throw new MissingCityNameException("city name is required");
        if (city.getLocation() == null)
            throw new MissingLocationException("location attribute is required");
        return cityRepo.save(city);
    }


    public Page<City> getAll(Pageable pageable) {

        return this.cityRepo.findAll(pageable);
    }

    public City searchCity(String name) {
        return this.cityRepo.findByName(name);
    }



    public Page<HotelDistanceDTO> getAllHotels(City city, Pageable pageable) {
        Pageable defaultPageable = PageRequest.of(0, 5, Sort.by("name").ascending());

        if (pageable == null) {
            pageable = defaultPageable;
        }

        List<Hotel> hotels = city.getHotels();
        List<HotelDistanceDTO> dtos = new ArrayList<>();
        for (Hotel hotel : hotels) {
            double distance = hotel.findDistanceFromCity(city).getDistance();
            HotelDistanceDTO dto = new HotelDistanceDTO(hotel, distance);
            dtos.add(dto);

        }
        dtos.sort(Comparator.comparingDouble(HotelDistanceDTO::getDistance));
        int pageSize = pageable.getPageSize();
        int pageNum = pageable.getPageNumber();
        int startItem = pageNum * pageSize;
        List<HotelDistanceDTO> pageOfDtos;
        if (dtos.size() < startItem) {
            pageOfDtos = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, dtos.size());
            pageOfDtos = dtos.subList(startItem, toIndex);
        }

        return (new PageImpl<>(pageOfDtos, pageable, dtos.size()));

    }
}
