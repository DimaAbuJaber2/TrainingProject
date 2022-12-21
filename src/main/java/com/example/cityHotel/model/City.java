package com.example.cityHotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name="city")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(name="City_name",unique = true)
        private String name;
        @Column(name="City_address")
        private String address;
        @Embedded
        private locations location;
        @JsonIgnore
        @OneToMany(mappedBy = "city",cascade = CascadeType.REMOVE)
        private List<Hotel> hotels;



}
