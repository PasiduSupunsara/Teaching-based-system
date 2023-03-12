package com.example.Teaching_based_system.Service;

import com.example.Teaching_based_system.Entity.*;
import com.example.Teaching_based_system.Repository.*;
import com.example.Teaching_based_system.RequestDTO.*;
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
    private AssesmentRepo assesmentRepo;
    @Autowired
    private Messagerepo messagerepo;
    @Autowired
    private UserRepo userRepo;

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

    public void createNewAssesment(AssesmentDTO assesmentDTO){
        assesmentRepo.save(modelMapper.map(assesmentDTO, Assesment.class));
    }
    public void putMessage(MessageDTO messageDTO){
        Message message1 = modelMapper.map(messageDTO, Message.class);
        int lastMid = (int)messagerepo.count();
        message1.setMid(lastMid + 1);
        User user = userRepo.findByName(messageDTO.getName());
        message1.setRid(user.getId());
        messagerepo.save(message1);
    }
}
