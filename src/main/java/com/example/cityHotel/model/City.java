package com.example.cityHotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Table(name="city",uniqueConstraints = @UniqueConstraint(columnNames = {"latitude","longitude"}))
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "A city with an id,  name, address, location and list of hotels ")
public class City {
        @ApiModelProperty(value = "The PK ID of the city")
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ApiModelProperty(value = "The name of the city")
        @Column(name="City_name",unique = true)
        private String name;

        @ApiModelProperty(value = "The address of the city")
        @Column(name="City_address")
        private String address;

        @ApiModelProperty(value = "The location of the city")
        @Embedded
        @NonNull
        private locations location;



        @ApiModelProperty(value = "List of hotels inside the city")
        @JsonManagedReference
        @OneToMany(mappedBy = "city",cascade = CascadeType.REMOVE)
        private List<Hotel> hotels;



}
