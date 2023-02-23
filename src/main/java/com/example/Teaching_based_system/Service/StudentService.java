package com.example.Teaching_based_system.Service;

import com.example.Teaching_based_system.Entity.Coursestudent;
import com.example.Teaching_based_system.Repository.StudentCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentCourseRepo studentCourseRepo;

    public Coursestudent saveCourseStudent(Coursestudent coursestudent){
        return studentCourseRepo.save(coursestudent);
    }
}

