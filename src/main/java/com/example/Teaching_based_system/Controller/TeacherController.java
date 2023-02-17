package com.example.Teaching_based_system.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {
    @GetMapping("/hello")
    public String sayhello(){
        return "hello";
    }

}
