package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.JWT.JwtTokenUtil;
import com.example.Teaching_based_system.RequestDTO.InputNameDTO;
import com.example.Teaching_based_system.RequestDTO.UpdateDTO;
import com.example.Teaching_based_system.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private UserService userService;

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody InputNameDTO inputNameDTO){
        return userService.deleteUser(inputNameDTO);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody UpdateDTO updateDTO){
        return userService.updateUser(updateDTO);
    }
}
