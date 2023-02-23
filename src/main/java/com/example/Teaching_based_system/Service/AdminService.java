package com.example.Teaching_based_system.Service;

import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.Repository.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CourseRepo courseRepo;

    public Course saveCourse(Course course){
        return courseRepo.save(course);
    }

}
