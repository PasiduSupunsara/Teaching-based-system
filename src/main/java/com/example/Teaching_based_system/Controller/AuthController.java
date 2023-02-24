package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.RequestDTO.*;
import com.example.Teaching_based_system.ResponseDTO.OutDTO;
import com.example.Teaching_based_system.ResponseDTO.ViewDTO;
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

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterDTO registerDTO){
        return userService.saveUser(registerDTO);
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }
    @GetMapping("/getAllStudent")
    public List<ViewDTO> getAllStudent(){
        return userService.findAll("STUDENT");
    }
    @GetMapping("/getAllTeachers")
    public List<ViewDTO> getAllTeachers(){
        return userService.findAll("TEACHER");
    }
    @GetMapping("/getAllAdmins")
    public List<ViewDTO> getAllAdmins(){
        return userService.findAll("ADMIN");
    }

    @GetMapping("/getCoursenameByUserId")
    public List<OutDTO> getCoursenameByUserId(){
        return studentService.getCoursenameByUserId();
    }

    @GetMapping("/findCourseNameByUserIdForTeacher")
    public List<OutDTO> findCourseNameByUserIdForTeacher(){
        return teacherService.findCourseNameByUserIdForTeacher();
    }




}
