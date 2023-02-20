package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Response.ViewDTO;
import com.example.Teaching_based_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private UserService userService;
    @GetMapping("/hello")
    public String sayhello(){
        return "hello";
    }
    @GetMapping("/getAllStudent")
    public List<ViewDTO> getAllStudent(){
        return userService.findAllStudent();
    }

}
