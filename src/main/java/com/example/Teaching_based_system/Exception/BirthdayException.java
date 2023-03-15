package com.example.Teaching_based_system.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SEE_OTHER)
public class BirthdayException extends RuntimeException{
    public BirthdayException(){
        super();
        System.out.println("ubwhew");
    }
}
