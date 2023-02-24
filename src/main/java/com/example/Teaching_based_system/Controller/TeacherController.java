package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Entity.Courseteacher;
import com.example.Teaching_based_system.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @PostMapping("/mapTeacherCourse")
    public Courseteacher saveCourseTeacher(@RequestBody Courseteacher courseteacher){
        return teacherService.saveCourseTeacher(courseteacher);
    }


}
