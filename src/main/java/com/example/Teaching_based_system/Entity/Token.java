package com.example.Teaching_based_system.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Token {
    @Id
    private String name;
    @Column(nullable = false)
    private String token;
}
