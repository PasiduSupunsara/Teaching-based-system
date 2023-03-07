package com.example.Teaching_based_system.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Assesment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int assid ;
    @Column(nullable = false)
    public int cid;
    @Column(nullable = false)
    public String assesmentname;
    @Column(nullable = false)
    public String details;

    @Column(nullable = false)
    private String duedate;
}
