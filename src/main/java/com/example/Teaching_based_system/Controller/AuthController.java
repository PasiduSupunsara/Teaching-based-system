package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.RequestDTO.*;
import com.example.Teaching_based_system.JWT.JwtTokenUtil;
import com.example.Teaching_based_system.Response.ViewDTO;
import com.example.Teaching_based_system.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
@CrossOrigin

public class AuthController {
    @Autowired
    private UserService userService;
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
        return userService.findAllStudent();
    }


}
