package com.example.cityHotel.service;

import com.example.cityHotel.dto.HotelDistanceDTO;
import com.example.cityHotel.exception.*;
import com.example.cityHotel.model.City;
import com.example.cityHotel.model.Hotel;
import com.example.cityHotel.repository.CityRepo;
import com.example.cityHotel.repository.HotelRepo;
import com.example.cityHotel.utility.CalculateDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private RatingService ratingService;


    public HotelDistanceDTO getHotelWithDistance(Integer id)
    {

        Hotel hotel = hotelRepo.findById(id).orElseThrow(()->new HotelNotFoundException("Hotel with id: "+id+" not found"));;
        double distance = hotel.findDistanceFromCity(hotel.getCity()).getDistance();
        HotelDistanceDTO hotelDistanceDTO = new HotelDistanceDTO(hotel, distance);
        return hotelDistanceDTO;

    }
    public Hotel getHotel(Integer id)
    {
        Hotel hotel = this.hotelRepo.findById(id).orElseThrow(()->new HotelNotFoundException("Hotel with id: "+id+" not found"));
        return hotel;


    }
    @Transactional
    public Hotel saveHotel(Hotel hotel) {
        City city = cityRepo.findById(hotel.getCity().getId()).orElse(null);
        if(city==null) throw new CityNotFoundException("city is requires");
        hotel.setCity(city);

     city.getHotels().add(hotel);
       //Save the city to update its list of hotels
      cityRepo.save(city);


      // save the hotel
        if(hotel.getName()==null) throw new MissingHotelNameException("hotel name is required");
        if(hotel.getLocation()==null)
            throw new MissingLocationException("location attribute is required");
       return  hotelRepo.save( hotel);


    }

    public List<Hotel> getHotelsInCity(City city) {

        return hotelRepo.findByCity(city);
    }
    @Transactional
    public void delete(Integer id)
    {
        this.hotelRepo.deleteById(id);
    }
    @Transactional
    public Hotel update(Hotel hotel)
    {
        if(hotel.getName()==null) throw new MissingHotelNameException("hotel name is required");
        if(hotel.getLocation()==null)
            throw new MissingLocationException("location attribute is required");
        if(hotel.getCity()==null) throw new CityNotFoundException("city is required");
        return this.hotelRepo.save(hotel);
    }


    public Page<HotelDistanceDTO> getHotelsInCityWithDistance(City city,Pageable pageable) {
        Pageable defaultPageable = PageRequest.of(0, 5, Sort.by("name").ascending());

        if (pageable == null) {
            pageable = defaultPageable;
        }
        if (city == null) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }

        // Retrieve a page of hotels for the given city
        List<Hotel> hotels = this.getHotelsInCity(city);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Hotel> pageOfHotels = hotels.subList(startItem, Math.min(startItem + pageSize, hotels.size()));
        Page<Hotel> hotelsPage = new PageImpl<>(pageOfHotels, pageable, hotels.size());

        // Create a list of HotelDistanceDTO objects from the page of hotels
        List<HotelDistanceDTO> hotelDistanceDTOs = new ArrayList<>();
        for (Hotel hotel : hotelsPage) {
            double distance = hotel.findDistanceFromCity(city).getDistance();
            HotelDistanceDTO dto = new HotelDistanceDTO(hotel, distance);
            hotelDistanceDTOs.add(dto);
        }

        // Sort the list of HotelDistanceDTO objects by distance
        List<HotelDistanceDTO> sortedHotels = hotelDistanceDTOs.stream()
                .sorted(Comparator.comparingDouble(HotelDistanceDTO::getDistance))
                .collect(Collectors.toList());

        // Return a new Page object containing the sorted list of HotelDistanceDTO objects
        return new PageImpl<>(sortedHotels, hotelsPage.getPageable(), hotelsPage.getTotalElements());
    }


        public Page<Hotel> getHotels(int page, int size, String sortBy, Sort.Direction direction) {
            Sort sort = Sort.by(direction, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            return hotelRepo.findAll(pageable);

    }


    public Page<HotelDistanceDTO> getHotelsWithDistance(int page, int size, String sortBy, Sort.Direction direction) {
        Pageable defaultPageable = PageRequest.of(0, 5, Sort.by("name").ascending());
        Page<Hotel> hotels = this.getHotels(page, size, sortBy, direction);
        List<HotelDistanceDTO> hotelDistanceDTOs = new ArrayList<>();
        for (Hotel hotel : hotels) {
            double distance = hotel.findDistanceFromCity(hotel.getCity()).getDistance();
            HotelDistanceDTO dto = new HotelDistanceDTO(hotel, distance);
            hotelDistanceDTOs.add(dto);
        }

        return new PageImpl<>(hotelDistanceDTOs, hotels.getPageable(), hotels.getTotalElements());
    }

    @Transactional
    public void rateHotel(Hotel hotel, int rating) {
        ratingService.rateHotel(hotel, rating);
    }


    public HotelDistanceDTO searchHotel(String name)
    {
       Hotel hotel=hotelRepo.findByName(name);
        double distance = hotel.findDistanceFromCity(hotel.getCity()).getDistance();
        HotelDistanceDTO hotelDistanceDTO = new HotelDistanceDTO(hotel, distance);
        return hotelDistanceDTO;

    }
}
