package com.example.Teaching_based_system.Service;

import com.example.Teaching_based_system.Entity.*;
import com.example.Teaching_based_system.Repository.*;
import com.example.Teaching_based_system.RequestDTO.Input2;
import com.example.Teaching_based_system.RequestDTO.InputId;
import com.example.Teaching_based_system.RequestDTO.Message2DTO;
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
    private Messagerepo messagerepo;
    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TeacherCourseRepo teacherCourseRepo;

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
    public void putMessage(Message2DTO messageDTO){
        Message message1 = modelMapper.map(messageDTO, Message.class);
        int cid = messageDTO.getCid();
        List<Integer> ids = teacherCourseRepo.teacherByCourseId(cid);
        for(int id:ids){
            message1.setRid(id);
            int lastMid = (int)messagerepo.count();
            message1.setMid(lastMid + 1);
            System.out.println(message1);
            messagerepo.save(message1);
        }
    }
    public List<Course> findAllCourse(){
        return courseRepo.findAllCourseForRegister(1);
    }
}

