package com.sky.SpringDemo.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Game found with that id")
public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException() {
    }

    public GameNotFoundException(String message) {
        super(message);
    }
}
