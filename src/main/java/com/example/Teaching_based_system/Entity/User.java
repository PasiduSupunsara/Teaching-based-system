package com.example.Teaching_based_system.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(unique = true,nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    private String role;
    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(unique = true,nullable = false)
    private String phoneNumber;

    @Column(unique = true,nullable = false)
    private String idNumber;

    @Column(nullable = false)
    private String dateOfBirth;

}
