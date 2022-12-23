package com.example.cityHotel.model;


import com.example.cityHotel.utility.CalculateDistance;
import com.example.cityHotel.dto.DistanceDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="hotel_name")
    private String name;
    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;
    @Embedded
    @NonNull
    private locations location;
    @JsonIgnore
    private int numRatings;
    private double rating;
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
