package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.Entity.Coursestudent;
import com.example.Teaching_based_system.RequestDTO.InputId;
import com.example.Teaching_based_system.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/findAllCoursesById")
    public List<Course> findAllCoursesById(@RequestBody InputId inputId){
        return studentService.findAllCoursesById(inputId);
    }



}
