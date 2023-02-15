package com.example.Teaching_based_system.Controller;
import com.example.Teaching_based_system.RequestDTO.InputNameDTO;
import com.example.Teaching_based_system.Response.ResponseDTO;
import com.example.Teaching_based_system.Response.ViewDTO;
import com.example.Teaching_based_system.Service.UserService;
import com.example.Teaching_based_system.Response.DetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping
@CrossOrigin
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public List<ViewDTO>  getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/getUser")
    public ResponseEntity getUser(@RequestBody InputNameDTO inputNameDTO){
        return userService.getDetails(inputNameDTO);
    }

}
