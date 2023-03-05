package com.example.Teaching_based_system.Service;

import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.Entity.Courseteacher;
import com.example.Teaching_based_system.Entity.MyTableId;
import com.example.Teaching_based_system.Entity.TeacherPrimary;
import com.example.Teaching_based_system.Repository.CourseRepo;
import com.example.Teaching_based_system.Repository.StudentCourseRepo;
import com.example.Teaching_based_system.Repository.TeacherCourseRepo;
import com.example.Teaching_based_system.RequestDTO.Input2;
import com.example.Teaching_based_system.RequestDTO.Input3;
import com.example.Teaching_based_system.RequestDTO.InputId;
import com.example.Teaching_based_system.ResponseDTO.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherCourseRepo teacherCourseRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private StudentCourseRepo studentCourseRepo;

    @Autowired
    private ModelMapper modelMapper;
    public Courseteacher saveCourseTeacher(Courseteacher courseteacher){
        return teacherCourseRepo.save(courseteacher);
    }
    public List<OutDTO> findCourseNameByUserIdForTeacher(){
        return teacherCourseRepo.findCourseNameByUserIdForTeacher();
    }
    public List<Out1DTO> findTeacherId(InputId inputId){
        return teacherCourseRepo.findTeacherId(inputId.getId());
    }
    public List<Out2DTO> findTeacherName(InputId inputId){
        return teacherCourseRepo.findTeacherName(inputId.getId());
    }

    public int countStudentByCourseId(InputId inputId){
        return studentCourseRepo.countStudentByCourseId(inputId.getId());
    }

    public  List<Course> findAllCoursesById(InputId inputId){
        return courseRepo.findAllCourseByIdforTeacher(inputId.getId());
    }

    public void deleteMapping(Input3 input3){
        teacherCourseRepo.deleteById(modelMapper.map(input3, TeacherPrimary.class));
    }

    public int CountCourseStudent(Input3 input3){
        return teacherCourseRepo.CountCourseTeacher(input3.getTid(), input3.getCourseid());
    }

    public List<ViewUserDTO> findAllByCourseid(InputId inputId){
        return courseRepo.findAllByCourseid(inputId.getId());
    }
}
