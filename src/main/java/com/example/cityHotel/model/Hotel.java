package com.example.cityHotel.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="hotel",uniqueConstraints = @UniqueConstraint(columnNames = {"id", "city_id"}))
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="hotel_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @Embedded
    private locations location;
}
