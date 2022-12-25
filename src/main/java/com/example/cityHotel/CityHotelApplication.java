package com.example.cityHotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(value = {"com.example.cityHotel.service","com.example.cityHotel.configuration","com.example.cityHotel.controller","com.example.cityHotel.repository","com.example.cityHotel.model"})
public class CityHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityHotelApplication.class, args);
	}

}
