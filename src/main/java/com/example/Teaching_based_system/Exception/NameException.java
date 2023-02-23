package com.example.Teaching_based_system.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NameException extends RuntimeException{
    public NameException(){
        super();
    }
}
