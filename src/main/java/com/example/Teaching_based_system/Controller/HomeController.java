package com.example.Teaching_based_system.Controller;
import com.example.Teaching_based_system.Service.UserService;
import com.example.Teaching_based_system.UserDTO.ResponseDTO;
import com.example.Teaching_based_system.UserDTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping
@CrossOrigin
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public List<ResponseDTO> getAllUsers(){
        List<ResponseDTO> userDTOList = userService.getAllUsers();
        return userDTOList;
    }
    @GetMapping("/getUser")
    public UserDTO getUser(String name){
        return userService.getDetails(name);
    }

}
