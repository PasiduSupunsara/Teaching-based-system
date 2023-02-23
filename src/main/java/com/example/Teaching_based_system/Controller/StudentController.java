package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Entity.Coursestudent;
import com.example.Teaching_based_system.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/mapStudentCourse")
    public Coursestudent saveCourseStudent(@RequestBody Coursestudent coursestudent){
        return studentService.saveCourseStudent(coursestudent);
    }



}
