package com.example.Teaching_based_system.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ViewDTO {
    private String name;
    private String firstName;
    private String lastName;
    private String role ;
    private String idNumber;
    private String email;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;
}
