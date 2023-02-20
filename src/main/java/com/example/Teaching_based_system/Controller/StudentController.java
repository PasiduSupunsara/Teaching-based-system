package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Response.ViewDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }



}
