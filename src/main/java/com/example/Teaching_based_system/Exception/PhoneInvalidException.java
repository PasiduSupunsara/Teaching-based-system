package com.example.Teaching_based_system.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.LENGTH_REQUIRED)
public class PhoneInvalidException extends RuntimeException{
    public PhoneInvalidException(){
        super();
    }
}