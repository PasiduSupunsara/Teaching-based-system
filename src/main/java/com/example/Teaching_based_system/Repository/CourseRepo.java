package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.ResponseDTO.Out3DTO;
import com.example.Teaching_based_system.ResponseDTO.ViewUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {

    Course findByCourseid(int courseid);
    @Query(value = "SELECT * from course where state = ?1",nativeQuery = true)
    List<Course> findAllCourseForRegister(int state);
    @Query(value = "SELECT u.* from  coursestudent cs join user u on cs.sid = u.id where cs.courseid = ?1",nativeQuery = true)
    List<ViewUserDTO> findAllByCourseid(int courseid);

    @Query(value = "SELECT co.* from coursestudent cs join course co on cs.courseid = co.courseid where cs.sid = ?1",nativeQuery = true)
    List<Course> findAllCourseById(int sid);

    @Query(value = "select * from course where courseid = ?1",nativeQuery = true)
    Course findCourseDetailsByCourseid(int courseid);

    @Query(value = "SELECT co.* from courseteacher ct join course co on ct.courseid = co.courseid where ct.tid = ?1",nativeQuery = true)
    List<Course> findAllCourseByIdforTeacher(int tid);

    @Query(value = "SELECT co.* from courseteacher ct join course co on ct.courseid = co.courseid where ct.tid = ?1",nativeQuery = true)
    List<Course> findAllCourseByTId(int tid);
}
