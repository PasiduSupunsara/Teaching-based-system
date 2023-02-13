package com.example.Teaching_based_system.Service;


import com.example.Teaching_based_system.Auth.AuthRequest;
import com.example.Teaching_based_system.Auth.AuthResponse;
import com.example.Teaching_based_system.Configuration.UserPrincipal;
import com.example.Teaching_based_system.Entity.User;
import com.example.Teaching_based_system.JWT.JwtTokenUtil;
import com.example.Teaching_based_system.Repository.UserRepo;
import com.example.Teaching_based_system.UserDTO.ResponseDTO;
import com.example.Teaching_based_system.UserDTO.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public ResponseEntity<?> deleteUser(@RequestBody UserDTO userDTO){
        if (userRepo.existsById(userDTO.getId())){
            userRepo.delete(modelMapper.map(userDTO, User.class));
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        }else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }
    public List<ResponseDTO> getAllUsers(){
        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList,new TypeToken<ArrayList<ResponseDTO>>(){
        }.getType());
    }


    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
        if (userRepo.existsById(userDTO.getId())){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encodedPassword);
            userRepo.save(modelMapper.map(userDTO, User.class));
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        }else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }

    public UserDTO getUser(int id){
        if(userRepo.existsById(id)){
            return modelMapper.map(userRepo.getReferenceById(id),UserDTO.class);
        }
        return null;
    }
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO){
        if (userRepo.existsById(userDTO.getId())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encodedPassword);
            userRepo.save(modelMapper.map(userDTO, User.class));
            String token = jwtTokenUtil.generateToken(userDTO.getName(),userDTO.getRole());
            AuthResponse authResponse = new AuthResponse(userDTO.getName(),token);
            return ResponseEntity.ok(authResponse);
        }
    }

    public UserDTO getDetails(String name){
        User user = userRepo.findByName(name);
        return modelMapper.map(user,UserDTO.class);
    }
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getName(),authRequest.getPassword())
            );
            UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(authRequest.getName(),getDetails(authRequest.getName()).getRole());
            AuthResponse authResponse = new AuthResponse(user.getUsername(),token);
            return ResponseEntity.ok(authResponse);
        }
        catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
