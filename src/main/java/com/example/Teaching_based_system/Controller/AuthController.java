package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.RequestDTO.LoginDTO;
import com.example.Teaching_based_system.RequestDTO.InputNameDTO;
import com.example.Teaching_based_system.RequestDTO.UpdateDTO;
import com.example.Teaching_based_system.JWT.JwtTokenUtil;
import com.example.Teaching_based_system.Service.UserService;
import com.example.Teaching_based_system.RequestDTO.RegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ModelMapper modelMapper;
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

}
