package com.example.Teaching_based_system.Controller;

import com.example.Teaching_based_system.Entity.Assesment;
import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.Entity.File;
import com.example.Teaching_based_system.Entity.Message;
import com.example.Teaching_based_system.Repository.AssesmentRepo;
import com.example.Teaching_based_system.Repository.FileRepo;
import com.example.Teaching_based_system.RequestDTO.*;
import com.example.Teaching_based_system.ResponseDTO.*;
import com.example.Teaching_based_system.Service.AdminService;
import com.example.Teaching_based_system.Service.StudentService;
import com.example.Teaching_based_system.Service.TeacherService;
import com.example.Teaching_based_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping
@CrossOrigin

public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;
    @Autowired
    private FileRepo fileRepo;

    @Autowired
    private AssesmentRepo assesmentRepo;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterDTO registerDTO){
        return userService.saveUser(registerDTO);
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @GetMapping("/getCoursenameByUserId")
    public List<OutDTO> getCoursenameByUserId(@RequestBody InputId inputId){
        return studentService.getCoursenameByUserId(inputId);
    }

    @GetMapping("/findCourseNameByUserIdForTeacher")
    public List<OutDTO> findCourseNameByUserIdForTeacher(){
        return teacherService.findCourseNameByUserIdForTeacher();
    }

    @GetMapping("/findTeacherId")
    public List<Out1DTO> findTeacherId(@RequestBody InputId inputId){
        return teacherService.findTeacherId(inputId);
    }

    @GetMapping("/findTeacherName")
    public List<Out2DTO> findTeacherName(@RequestBody InputId inputId){
        return teacherService.findTeacherName(inputId);
    }
    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses(){
        return adminService.getAllcourses();
    }

    @PostMapping("/getAllStudentForCourse")
    public List<ViewUserDTO> findAllByCourseid(@RequestBody InputId inputId){
        return teacherService.findAllByCourseid(inputId);
    }
    @PostMapping("/getAllAssesmentByCid")
    public List<Assesment> getAllAssesmentByCid(@RequestBody InputIDwithPriDTO inputIDwithPriDTO){
        return userService.getAllAssesmentByCid(inputIDwithPriDTO);
    }
    @PostMapping("/getMessages")
    public List<NotificationDTO> getmessage(@RequestBody InputId inputId){
        return userService.getmessage(inputId);
    }

    @PostMapping("/countMessages")
    public int CountByRid(@RequestBody InputId inputId){
        return userService.CountByRid(inputId);
    }

    @PutMapping("/updateStatus")
    public void update(@RequestBody StatusUpdateDTO statusUpdateDTO){
        userService.updateMessageStatus(statusUpdateDTO);
    }

    @PostMapping("/getstatus")
    public int update(@RequestBody InputId inputId){
       return userService.getstatus(inputId.getId());
    }
    @PostMapping("/putMessageCourse")
    public void update(@RequestBody Message1DTO message1DTO){
         userService.putMessageCourse(message1DTO);
    }
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam("assid") int assid, @RequestParam("sid") int sid,@RequestParam("cid") int cid) {
        System.out.println(file);
        try {
            // Create a new file entity
            File file1 = new File();
            file1.setFilename(file.getOriginalFilename());
            file1.setContentType(file.getContentType());
            file1.setData(file.getBytes());
            file1.setAssid(assid);
            file1.setSid(sid);
            file1.setCid(cid);


            // Save the file entity to the database
            file1 = fileRepo.save(file1);

            // Return a success response
            return ResponseEntity.ok("File uploaded successfully with ID " + file1.getId());
        } catch (IOException e) {
            // Handle file save error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }

}
