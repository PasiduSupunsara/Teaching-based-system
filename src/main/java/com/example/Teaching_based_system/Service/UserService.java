package com.example.Teaching_based_system.Service;

import com.example.Teaching_based_system.RequestDTO.LoginDTO;
import com.example.Teaching_based_system.Response.ResponseDTO;
import com.example.Teaching_based_system.Configuration.UserPrincipal;
import com.example.Teaching_based_system.Entity.User;
import com.example.Teaching_based_system.JWT.JwtTokenUtil;
import com.example.Teaching_based_system.Repository.UserRepo;
import com.example.Teaching_based_system.RequestDTO.RegisterDTO;
import org.modelmapper.ModelMapper;
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
            ResponseDTO responseDTO = new ResponseDTO(token,null,null);
            return ResponseEntity.ok(responseDTO);
        }
        catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    public ResponseEntity<?> saveUser(RegisterDTO registerDTO){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(registerDTO.getPassword());
            registerDTO.setPassword(encodedPassword);
            userRepo.save(modelMapper.map(registerDTO, User.class));
            String token = jwtTokenUtil.generateToken(registerDTO.getName(), registerDTO.getRole());
            ResponseDTO responseDTO = new ResponseDTO(token,null,null);
            return ResponseEntity.ok(responseDTO);

    }

}
