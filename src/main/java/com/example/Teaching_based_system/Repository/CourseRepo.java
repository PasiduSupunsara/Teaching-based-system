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
    @Query(value = "SELECT u.name,u.id from  coursestudent cs join user u on cs.sid = u.id where cs.courseid = 11",nativeQuery = true)
    List<Out3DTO> findAllByCourseid();
}
