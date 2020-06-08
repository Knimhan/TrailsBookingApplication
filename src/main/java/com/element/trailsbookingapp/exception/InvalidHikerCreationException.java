package com.element.trailsbookingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidHikerCreationException extends RuntimeException {
    public InvalidHikerCreationException(String message) {
        super(message);
    }
}
