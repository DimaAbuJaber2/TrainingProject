package com.example.cityHotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingLocationException extends RuntimeException {
    public MissingLocationException(String message)
    {
        super(message);
    }
}
