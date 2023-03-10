package com.example.Teaching_based_system.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mid ;
    @Column(nullable = false)
    private int sid;
    @Column(nullable = false)
    private int rid;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private int status;

}
