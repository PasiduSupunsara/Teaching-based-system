package com.example.Teaching_based_system.Service;

import com.example.Teaching_based_system.Entity.Assesment;
import com.example.Teaching_based_system.Entity.Message;
import com.example.Teaching_based_system.Exception.*;
import com.example.Teaching_based_system.Repository.*;
import com.example.Teaching_based_system.RequestDTO.*;
import com.example.Teaching_based_system.ResponseDTO.ResponseDTO;
import com.example.Teaching_based_system.Configuration.UserPrincipal;
import com.example.Teaching_based_system.Entity.User;
import com.example.Teaching_based_system.JWT.JwtTokenUtil;
import com.example.Teaching_based_system.ResponseDTO.ViewDTO;
import com.example.Teaching_based_system.ResponseDTO.ViewUserDTO;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AssesmentRepo assesmentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private StudentCourseRepo studentCourseRepo;

    @Autowired
    private TeacherCourseRepo teacherCourseRepo;
    @Autowired
    private Messagerepo messagerepo;

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
            ResponseDTO responseDTO = new ResponseDTO(token,refreshToken,null,null,getDetail(loginDTO.getName()).getRole(),getDetail(loginDTO.getName()).getId());
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
    public static Boolean isValidName(String name){
        if (name.length() == 0 || name.length() == 1){
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i) )){
                return false;
            }
        }
        return true;
    }
    public static Boolean isValidUserName(String name){
        if (name.length() == 0 || name.length() == 1){
            return false;
        } else if (!Character.isLetter(name.charAt(0))) {
            return false;
        }
        for (int i = 1; i < name.length(); i++) {
            if (!(Character.isLetter(name.charAt(i)) || Character.isDigit(name.charAt(i)))){
                return false;
            }
        }
        return true;
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
        else if(!isValidUserName(registerDTO.getName())){
            throw new UserNameException();
        }
        else if(!isValidName(registerDTO.getFirstName())){
            throw new NameException();
        }
        else if(!isValidName(registerDTO.getLastName())){
            throw new NameException();
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
            if ((inputNameDTO.getName()).equals((inputNameDTO.getPrincipalName()))){
                throw new UsercanthandleException();
            }
            else{
                userRepo.delete(modelMapper.map(registerDTO1, User.class));
                ResponseDTO responseDTO = new ResponseDTO(null,null);
                return ResponseEntity.ok(responseDTO);
            }


        }else {
            ResponseDTO responseDTO = new ResponseDTO("error",null);
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    public ResponseEntity updateUser(@RequestBody UpdateDTO updateDTO){
        User user = userRepo.findByName(updateDTO.getName());
        User user1 = modelMapper.map(user, User.class);
        if (userRepo.existsById(user1.getId())){
            if((updateDTO.getName()).equals(updateDTO.getPrincipalName())){
                throw new UsercanthandleException();
            }
            else{user1.setRole(updateDTO.getNewRole());
                userRepo.save(modelMapper.map(user1, User.class));
                ResponseDTO responseDTO = new ResponseDTO(null,null);
                return ResponseEntity.ok(responseDTO);}


        }else {
            ResponseDTO responseDTO = new ResponseDTO("error",null);
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
    public List<ViewUserDTO> getAllUsers(){
        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList,new TypeToken<ArrayList<ViewDTO>>(){
        }.getType());
    }
    public List<ViewUserDTO> findAll(String role){
        List<User> userList = userRepo.findAllStudent(role);
        return modelMapper.map(userList,new TypeToken<ArrayList<ViewDTO>>(){
        }.getType());
    }

    public List<Assesment> getAllAssesmentByCid(InputIDwithPriDTO inputIDwithPriDTO){
        int pid = inputIDwithPriDTO.getPrincipalid();
        int cid = inputIDwithPriDTO.getId();
        if ((studentCourseRepo.CountCourseStudent(pid,cid) == 1) || (teacherCourseRepo.CountCourseTeacher(pid,cid) == 1)){
            return assesmentRepo.getAllAssesmentByCid(cid);
        }
        else{
            return null;
        }
    }
    public List<Assesment> getTimeLine(InputId inputId){
        return assesmentRepo.getTimeLine(LocalDate.now().plusWeeks(1),LocalDate.now(),inputId.getId());
    }

    public List<Assesment> getTimeLineForTeacher(InputId inputId){
        return assesmentRepo.getTimeLineForTeacher(LocalDate.now().plusWeeks(1),LocalDate.now(),inputId.getId());
    }

    public List<Message> getmessage(InputId inputId){
       return messagerepo.findAllByRid(inputId.getId());
    }
    public int CountByRid(InputId inputId){
        return messagerepo.CountByRid(inputId.getId());
    }
    public void updateMessageStatus(StatusUpdateDTO statusUpdateDTO){
        Message message = messagerepo.getReferenceById(statusUpdateDTO.getMid());
        message.setStatus(statusUpdateDTO.getCount());
        messagerepo.save(message);
    }
    public int getstatus(int mid){
        return messagerepo.getstatus(mid);
    }

}
