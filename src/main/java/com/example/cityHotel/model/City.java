package com.example.cityHotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="City")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
        @Id
        @Column(name="City_name")
        private String name;
        @Column(name="City_address")
        private String address;

}
