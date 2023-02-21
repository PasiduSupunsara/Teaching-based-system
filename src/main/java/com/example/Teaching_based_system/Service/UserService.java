package com.example.Teaching_based_system.Service;

import com.example.Teaching_based_system.Exception.IdInvalidException;
import com.example.Teaching_based_system.Exception.PasswordInvalidException;
import com.example.Teaching_based_system.Exception.PhoneInvalidException;
import com.example.Teaching_based_system.Exception.UserNameException;
import com.example.Teaching_based_system.RequestDTO.*;
import com.example.Teaching_based_system.Response.ResponseDTO;
import com.example.Teaching_based_system.Configuration.UserPrincipal;
import com.example.Teaching_based_system.Entity.User;
import com.example.Teaching_based_system.JWT.JwtTokenUtil;
import com.example.Teaching_based_system.Repository.UserRepo;
import com.example.Teaching_based_system.Response.ViewDTO;
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

    public RegisterDTO getDetail(String name){
        User user = userRepo.findByName(name);
        return modelMapper.map(user, RegisterDTO.class);
    }


    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getName(), loginDTO.getPassword())
            );
            UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(loginDTO.getName(),getDetail(loginDTO.getName()).getRole());
            String refreshToken = jwtTokenUtil.generateRefreshToken(loginDTO.getName(),getDetail(loginDTO.getName()).getRole());
            ResponseDTO responseDTO = new ResponseDTO(token,refreshToken,null,null,getDetail(loginDTO.getName()).getRole());
            return ResponseEntity.ok(responseDTO);
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
    public static boolean isValidName(String name) {
        String regex ="^(?=.*[a-z])(?=.*[A-Z])" + "(?=\\S+$)$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);
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
    public ResponseEntity<?> saveUser(RegisterDTO registerDTO){
        if(!isValidPassword(registerDTO.getPassword())){
            throw new PasswordInvalidException();
        }
        else if(!isValidPhoneNumber(registerDTO.getPhoneNumber())){
            throw new PhoneInvalidException();
        }
        else if (!isValidId(registerDTO.getIdNumber())){
            throw new IdInvalidException();
        }
        else if(!isValidName(registerDTO.getName())){
            throw new UserNameException();
        }
        else if(!isValidName(registerDTO.getFirstName())){
            throw new UserNameException();
        }
        else if(!isValidName(registerDTO.getLastName())){
            throw new UserNameException();
        }
        else{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(registerDTO.getPassword());
            registerDTO.setPassword(encodedPassword);
            userRepo.save(modelMapper.map(registerDTO, User.class));
            String token = jwtTokenUtil.generateToken(registerDTO.getName(), registerDTO.getRole());
            ResponseDTO responseDTO = new ResponseDTO(null,null);
            return ResponseEntity.ok(responseDTO);
        }


    }
    public ResponseEntity deleteUser(@RequestBody InputNameDTO inputNameDTO){
        User user = userRepo.findByName(inputNameDTO.getName());
        RegisterDTO registerDTO1 = modelMapper.map(user, RegisterDTO.class);
        if (userRepo.existsById(registerDTO1.getId())){
            userRepo.delete(modelMapper.map(registerDTO1, User.class));
            ResponseDTO responseDTO = new ResponseDTO(null,null);
            return ResponseEntity.ok(responseDTO);

        }else {
            ResponseDTO responseDTO = new ResponseDTO("error",null);
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    public ResponseEntity updateUser(@RequestBody UpdateDTO updateDTO){
        User user = userRepo.findByName(updateDTO.getName());
        User user1 = modelMapper.map(user, User.class);
        if (userRepo.existsById(user1.getId())){
            user1.setRole(updateDTO.getNewRole());
            userRepo.save(modelMapper.map(user1, User.class));
            ResponseDTO responseDTO = new ResponseDTO(null,null);
            return ResponseEntity.ok(responseDTO);

        }else {
            ResponseDTO responseDTO = new ResponseDTO("error",null);
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
    public List<ViewDTO> getAllUsers(){
        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList,new TypeToken<ArrayList<ViewDTO>>(){
        }.getType());
    }
    public List<ViewDTO> findAll(String role){
        List<User> userList = userRepo.findAllStudent(role);
        System.out.println(userList);
        return modelMapper.map(userList,new TypeToken<ArrayList<ViewDTO>>(){
        }.getType());
    }


}
