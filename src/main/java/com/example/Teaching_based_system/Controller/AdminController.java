package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.Entity.User;
import com.example.Teaching_based_system.RequestDTO.InputId;
import com.example.Teaching_based_system.RequestDTO.InputNameDTO;
import com.example.Teaching_based_system.RequestDTO.UpdateDTO;
import com.example.Teaching_based_system.ResponseDTO.Out3DTO;
import com.example.Teaching_based_system.ResponseDTO.ViewDTO;
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
    public List<ViewDTO> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/saveCourse")
    public Course saveCourse(@RequestBody Course course){
        System.out.println(course);
        return adminService.saveCourse(course);
    }
    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses(){
        return adminService.getAllcourses();
    }
    @GetMapping("/getAllStudentForCourse")
    public List<Out3DTO> findAllByCourseid(){
        return adminService.findAllByCourseid();
    }
}
