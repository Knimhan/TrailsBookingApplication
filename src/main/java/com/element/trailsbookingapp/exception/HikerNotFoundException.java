package com.element.trailsbookingapp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HikerNotFoundException extends RuntimeException {
    public HikerNotFoundException(String message) {
        super(message);
    }
}
