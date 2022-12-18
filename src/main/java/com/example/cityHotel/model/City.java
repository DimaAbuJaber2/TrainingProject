package com.example.cityHotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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



}
