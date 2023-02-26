package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.Entity.User;
import com.example.Teaching_based_system.ResponseDTO.Out3DTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {
    @Query(value = "SELECT u.* from  coursestudent cs join user u on cs.sid = u.id where cs.courseid = ?1",nativeQuery = true)
    List<Out3DTO> findAllByCourseid(int courseid);

    @Query(value = "SELECT co.* from coursestudent cs join course co on cs.courseid = co.courseid where cs.sid = ?1",nativeQuery = true)
    List<Course> findAllCourseById(int sid);
}
