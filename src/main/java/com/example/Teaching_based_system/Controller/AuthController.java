package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Entity.Assesment;
import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.RequestDTO.*;
import com.example.Teaching_based_system.ResponseDTO.*;
import com.example.Teaching_based_system.Service.AdminService;
import com.example.Teaching_based_system.Service.StudentService;
import com.example.Teaching_based_system.Service.TeacherService;
import com.example.Teaching_based_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
@CrossOrigin

public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterDTO registerDTO){
        return userService.saveUser(registerDTO);
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @GetMapping("/getCoursenameByUserId")
    public List<OutDTO> getCoursenameByUserId(@RequestBody InputId inputId){
        return studentService.getCoursenameByUserId(inputId);
    }

    @GetMapping("/findCourseNameByUserIdForTeacher")
    public List<OutDTO> findCourseNameByUserIdForTeacher(){
        return teacherService.findCourseNameByUserIdForTeacher();
    }

    @GetMapping("/findTeacherId")
    public List<Out1DTO> findTeacherId(@RequestBody InputId inputId){
        return teacherService.findTeacherId(inputId);
    }

    @GetMapping("/findTeacherName")
    public List<Out2DTO> findTeacherName(@RequestBody InputId inputId){
        return teacherService.findTeacherName(inputId);
    }
    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses(){
        return adminService.getAllcourses();
    }

    @PostMapping("/getAllStudentForCourse")
    public List<ViewUserDTO> findAllByCourseid(@RequestBody InputId inputId){
        return teacherService.findAllByCourseid(inputId);
    }
    @PostMapping("/getAllAssesmentByCid")
    public List<Assesment> getAllAssesmentByCid(@RequestBody InputIDwithPriDTO inputIDwithPriDTO){
        return userService.getAllAssesmentByCid(inputIDwithPriDTO);
    }
}
