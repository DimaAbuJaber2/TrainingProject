package com.example.cityHotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingCityNameException extends RuntimeException{
    public MissingCityNameException(String message)
    {
        super(message);
    }
}
