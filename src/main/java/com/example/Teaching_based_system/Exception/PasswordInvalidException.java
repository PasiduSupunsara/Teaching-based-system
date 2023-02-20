package com.example.Teaching_based_system.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CREATED)
public class PasswordInvalidException extends RuntimeException{
    public PasswordInvalidException(){
        super();
    }
}
