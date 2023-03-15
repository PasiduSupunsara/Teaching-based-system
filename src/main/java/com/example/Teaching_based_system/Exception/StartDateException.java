package com.example.Teaching_based_system.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.TOO_EARLY)
public class StartDateException extends RuntimeException{
    public StartDateException(){
        super();
    }
}
