package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.Entity.Coursestudent;
import com.example.Teaching_based_system.Repository.CourseRepo;
import com.example.Teaching_based_system.Repository.StudentCourseRepo;
import com.example.Teaching_based_system.RequestDTO.Input2;
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
    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private StudentCourseRepo studentCourseRepo;
    @PostMapping("/mapStudentCourse")
    public Coursestudent saveCourseStudent(@RequestBody Coursestudent coursestudent){
        if (studentCourseRepo.existsById(coursestudent.getId())){
            System.out.println("error");
            return null;
        }
        return studentService.saveCourseStudent(coursestudent);
    }
    @PostMapping("/findAllCoursesById")
    public List<Course> findAllCoursesById(@RequestBody InputId inputId){
        return studentService.findAllCoursesById(inputId);
    }
    @GetMapping("/getAllCourses")
    public List<Course> getAllcourses(){
        return courseRepo.findAll();
    }

    @PostMapping("/CountCourseStudent")
    public int CountCourseStudent(@RequestBody Input2 input2){
        return studentService.CountCourseStudent(input2);
    }
    @DeleteMapping("/deleteMappingstudentcourse")
    public void deleteMapping(@RequestBody Input2 input2){
        System.out.println(input2);
        studentService.deleteMapping(input2);
    }




}
