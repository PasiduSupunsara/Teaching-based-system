package com.example.Teaching_based_system.Service;

import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.Entity.Coursestudent;
import com.example.Teaching_based_system.Entity.MyTableId;
import com.example.Teaching_based_system.Entity.User;
import com.example.Teaching_based_system.Repository.CourseRepo;
import com.example.Teaching_based_system.Repository.StudentCourseRepo;
import com.example.Teaching_based_system.Repository.UserRepo;
import com.example.Teaching_based_system.RequestDTO.Input2;
import com.example.Teaching_based_system.RequestDTO.InputId;
import com.example.Teaching_based_system.RequestDTO.NameDTO;
import com.example.Teaching_based_system.ResponseDTO.OutDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentCourseRepo studentCourseRepo;
    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public Coursestudent saveCourseStudent(Coursestudent coursestudent){
        return studentCourseRepo.save(coursestudent);
    }
    public List<OutDTO> getCoursenameByUserId(InputId inputId){
        return studentCourseRepo.findCourseNameByUserId(inputId.getId());
    }

    public  List<Course> findAllCoursesById(InputId inputId){
        return courseRepo.findAllCourseById(inputId.getId());
    }


    public int CountCourseStudent(Input2 input2){
        return studentCourseRepo.CountCourseStudent(input2.getSid(), input2.getCourseid());
    }
    public void deleteMapping(Input2 input2){
        studentCourseRepo.deleteById(modelMapper.map(input2, MyTableId.class));
    }

    public Course findByCourseId(InputId inputId){
        return courseRepo.findCourseDetailsByCourseid(inputId.getId());
    }
}

