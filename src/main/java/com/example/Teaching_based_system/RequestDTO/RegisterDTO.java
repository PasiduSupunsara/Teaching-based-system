package com.example.Teaching_based_system.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class RegisterDTO {
    private int id = 0;
    private String name;
    private String password;
    private String firstName;
    private String lastName;
    private String role = "STUDENT";
    private String idNumber;
    private String email;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;

}
