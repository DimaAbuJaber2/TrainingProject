package com.example.cityHotel.model;


import com.example.cityHotel.utility.CalculateDistance;
import com.example.cityHotel.dto.DistanceDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Table(name="hotel",uniqueConstraints = @UniqueConstraint(columnNames = { "city_id","latitude","longitude"}))
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "A Hotel with an id,  name, location , city , rating and city which included this hotel with it's details ")
public class Hotel {

    @ApiModelProperty(value = "The PK ID of the hotel")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ApiModelProperty(value = "The name of the hotel")
    @Column(name="hotel_name")
    private String name;
    @ApiModelProperty(value = "the city of the hotel")
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;

    @ApiModelProperty(value = "The location of the hotel")
    @Embedded
    @NonNull
    private locations location;
    @JsonIgnore
    private int numRatings;


    @ApiModelProperty(value = "The rating of the city")
    private double rating;


    public Hotel(Integer id, String name, City city, @NonNull locations location, int numRatings, double rating) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.location = location;
        this.numRatings = numRatings;
        this.rating = rating;
    }



    public DistanceDTO findDistanceFromCity(City city)
    {
        double cityLat = city.getLocation().getLatitude();
        double cityLon = city.getLocation().getLongitude();
        double hotelLat = this.location.getLatitude();
        double hotelLon = this.location.getLongitude();
        double distance= CalculateDistance.distance(cityLat,cityLon,hotelLat,hotelLon);
        return new DistanceDTO(distance);
    }

}
