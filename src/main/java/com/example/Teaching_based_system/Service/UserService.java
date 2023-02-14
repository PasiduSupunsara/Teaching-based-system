package com.example.Teaching_based_system.Service;


import com.example.Teaching_based_system.Auth.AuthRequest;
import com.example.Teaching_based_system.Auth.AuthResponse;
import com.example.Teaching_based_system.Auth.DeleteRequest;
import com.example.Teaching_based_system.Auth.UpdateRequest;
import com.example.Teaching_based_system.Configuration.UserPrincipal;
import com.example.Teaching_based_system.Entity.User;
import com.example.Teaching_based_system.Exception.IdInvalidException;
import com.example.Teaching_based_system.Exception.PasswordInvalidException;
import com.example.Teaching_based_system.Exception.PhoneInvalidException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public ResponseEntity<?> deleteUser(@RequestBody DeleteRequest deleteRequest){
        User user = userRepo.findByName(deleteRequest.getName());
        UserDTO userDTO1 = modelMapper.map(user, UserDTO.class);
        if (userRepo.existsById(userDTO1.getId())){
            userRepo.delete(modelMapper.map(userDTO1, User.class));
            return  ResponseEntity.ok("ok");

        }else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }
    public List<ResponseDTO> getAllUsers(){
        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList,new TypeToken<ArrayList<ResponseDTO>>(){
        }.getType());
    }

    public ResponseEntity<?> updateUser(@RequestBody UpdateRequest updateRequest){
        User user = userRepo.findByName(updateRequest.getName());
        User user1 = modelMapper.map(user, User.class);
        System.out.println(user1.getPassword());
        if (userRepo.existsById(user1.getId())){
            user1.setRole(updateRequest.getNewRole());
            userRepo.save(modelMapper.map(user1, User.class));
            return ResponseEntity.ok("ok");

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
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
    public static boolean isValidPhoneNumber(String phonenumber) {
        int len = phonenumber.length();
        return (len == 10 && onlyDigits(phonenumber,len) && phonenumber.charAt(0) == '0');
    }
    public static boolean isValidId(String id) {
        int len = id.length();
        return (len == 12 && onlyDigits(id,len));
    }
    public static boolean onlyDigits(String str, int n)
    {
        for (int i = 0; i < n; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO){
        if(!isValidPassword(userDTO.getPassword())){
            throw new PasswordInvalidException();
        }
        else if(!isValidPhoneNumber(userDTO.getPhoneNumber())){
            throw new PhoneInvalidException();
        }
        else if (!isValidId(userDTO.getIdNumber())){
            throw new IdInvalidException();

        }
        else{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encodedPassword);
            userRepo.save(modelMapper.map(userDTO, User.class));
            String token = jwtTokenUtil.generateToken(userDTO.getName(),userDTO.getRole());
            AuthResponse authResponse = new AuthResponse(userDTO.getName(),token);
            return ResponseEntity.ok(authResponse);
        }


    }

}
