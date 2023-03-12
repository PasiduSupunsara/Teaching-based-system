package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.Entity.User;
import com.example.Teaching_based_system.RequestDTO.*;
import com.example.Teaching_based_system.ResponseDTO.Out3DTO;
import com.example.Teaching_based_system.ResponseDTO.ViewDTO;
import com.example.Teaching_based_system.ResponseDTO.ViewUserDTO;
import com.example.Teaching_based_system.Service.AdminService;
import com.example.Teaching_based_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody InputNameDTO inputNameDTO){
        return userService.deleteUser(inputNameDTO);
    }
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody UpdateDTO updateDTO){
        return userService.updateUser(updateDTO);
    }
    @GetMapping("/getAllUsers")
    public List<ViewUserDTO> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/getAllStudent")
    public List<ViewUserDTO> getAllStudent(){
        return userService.findAll("STUDENT");
    }
    @GetMapping("/getAllTeachers")
    public List<ViewUserDTO> getAllTeachers(){
        return userService.findAll("TEACHER");
    }
    @GetMapping("/getAllAdmins")
    public List<ViewUserDTO> getAllAdmins(){
        return userService.findAll("ADMIN");
    }

    @GetMapping("/getAllStudentForCourse")
    public List<ViewUserDTO> findAllByCourseid(@RequestBody InputId inputId){
        return adminService.findAllByCourseid(inputId);
    }
    @PostMapping("/saveCourse")
    public Course saveCourse(@RequestBody Course course){
        System.out.println(course);
        return adminService.saveCourse(course);
    }

    @PostMapping("/findAllCoursesById")
    public List<Course> findAllCoursesById(@RequestBody NameDTO nameDTO){
        return adminService.findAllCoursesById(nameDTO);
    }

    @PostMapping("/findAllCoursesByTId")
    public List<Course> findAllCoursesByTId(@RequestBody NameDTO nameDTO){
        return adminService.findAllCoursesByTId(nameDTO);
    }
    @PostMapping("/putMessageByRole")
    public void putMessageRole(@RequestBody MessageDTO messageDTO){
        adminService.putMessageRole(messageDTO);
    }
    @PostMapping("/putMessage")
    public void putMessage(@RequestBody MessageDTO messageDTO){
        adminService.putMessage(messageDTO);
    }
}
