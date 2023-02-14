package com.example.Teaching_based_system.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.MULTI_STATUS)
public class IdInvalidException extends RuntimeException{
    public IdInvalidException(){
        super();
    }
}
