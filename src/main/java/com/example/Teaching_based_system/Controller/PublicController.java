package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.RequestDTO.InputId;
import com.example.Teaching_based_system.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common")
@CrossOrigin
public class PublicController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/findByCourseId")
    public Course findByCourseId(@RequestBody InputId inputId){
        return studentService.findByCourseId(inputId);
    }
}
