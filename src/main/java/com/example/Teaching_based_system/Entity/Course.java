package com.example.Teaching_based_system.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    @Id
    private int courseid;
    private String coursename;
    private String medium;
    private String description;
    private LocalDate startdate;
    private String duration;
    private String fee;
    private int state = 0;
}
