package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Entity.Assesment;
import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.Entity.Courseteacher;
import com.example.Teaching_based_system.Repository.CourseRepo;
import com.example.Teaching_based_system.Repository.FileRepo;
import com.example.Teaching_based_system.RequestDTO.*;
import com.example.Teaching_based_system.ResponseDTO.Out3DTO;
import com.example.Teaching_based_system.ResponseDTO.ViewDTO;
import com.example.Teaching_based_system.ResponseDTO.ViewUserDTO;
import com.example.Teaching_based_system.Service.StudentService;
import com.example.Teaching_based_system.Service.TeacherService;
import com.example.Teaching_based_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private FileRepo fileRepo;

    @GetMapping("/getAllStudent")
    public List<ViewUserDTO> getAllStudent(){
        return userService.findAll("STUDENT");
    }
    @PostMapping("/mapTeacherCourse")
    public Courseteacher saveCourseTeacher(@RequestBody Courseteacher courseteacher){
        return teacherService.saveCourseTeacher(courseteacher);
    }
    @PostMapping("/findAllCoursesById")
    public List<Course> findAllCoursesById(@RequestBody InputId inputId){
        return teacherService.findAllCoursesById(inputId);
    }

    @GetMapping("/countStudentByCourseId")
    public int countStudentByCourseId(@RequestBody InputId inputId){
        return teacherService.countStudentByCourseId(inputId);
    }
    @GetMapping("/getAllCourses")
    public List<Course> getAllcourses(){
        return teacherService.findAllCourse();
    }

    @DeleteMapping("/deleteMappingteachercourse")
    public void deleteMapping(@RequestBody Input3 input3){
        teacherService.deleteMapping(input3);
    }
    @PostMapping("/CountCourseTeacher")
    public int CountCourseStudent(@RequestBody Input3 input3){
        return teacherService.CountCourseStudent(input3);
    }

    @PostMapping("/CreateNewAssesment")
    public void createNewAssesment(@RequestBody AssesmentDTO assesmentDTO){
        teacherService.createNewAssesment(assesmentDTO);
    }
    @PostMapping("/getTimeLine")
    public List<Assesment> getTimeLine(@RequestBody InputId inputId){
        return userService.getTimeLineForTeacher(inputId);
    }
    @PostMapping("/putMessage")
    public void putMessage(@RequestBody MessageDTO messageDTO){
        teacherService.putMessage(messageDTO);
    }

    @PostMapping("/getSubmissions")
    public List<Integer> getSubmissions(@RequestBody Input5 input5){
        return fileRepo.findSubmissions(input5.getCourseid(), input5.getAssid());
    }




}
