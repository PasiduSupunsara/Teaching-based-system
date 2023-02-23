package com.example.Teaching_based_system.Service;

import com.example.Teaching_based_system.Entity.Courseteacher;
import com.example.Teaching_based_system.Repository.TeacherCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TeacherService {
    @Autowired
    private TeacherCourseRepo teacherCourseRepo;
    @PostMapping("/mapTeacherCourse")
    public Courseteacher saveCourseTeacher(Courseteacher courseteacher){
        return teacherCourseRepo.save(courseteacher);
    }

}
