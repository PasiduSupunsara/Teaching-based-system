package com.example.Teaching_based_system.Service;

import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.Entity.Coursestudent;
import com.example.Teaching_based_system.Repository.CourseRepo;
import com.example.Teaching_based_system.Repository.StudentCourseRepo;
import com.example.Teaching_based_system.RequestDTO.InputId;
import com.example.Teaching_based_system.ResponseDTO.OutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentCourseRepo studentCourseRepo;
    @Autowired
    private CourseRepo courseRepo;

    public Coursestudent saveCourseStudent(Coursestudent coursestudent){
        return studentCourseRepo.save(coursestudent);
    }
    public List<OutDTO> getCoursenameByUserId(InputId inputId){
        return studentCourseRepo.findCourseNameByUserId(inputId.getId());
    }
    public  List<Course> findAllCoursesById(InputId inputId){
        return courseRepo.findAllCourseById(inputId.getId());
    }
}

