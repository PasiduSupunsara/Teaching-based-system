package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Auth.AuthRequest;
import com.example.Teaching_based_system.Auth.AuthResponse;
import com.example.Teaching_based_system.Configuration.UserPrincipal;
import com.example.Teaching_based_system.Entity.User;
import com.example.Teaching_based_system.JWT.JwtTokenUtil;
import com.example.Teaching_based_system.Service.UserService;
import com.example.Teaching_based_system.UserDTO.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;



@RestController
@RequestMapping
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
    public ResponseEntity signup(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        return userService.login(authRequest);

    }
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody UserDTO userDTO){
        return userService.deleteUser(userDTO);

    }
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }


}
