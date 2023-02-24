package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.Coursestudent;
import com.example.Teaching_based_system.Entity.MyTableId;
import com.example.Teaching_based_system.ResponseDTO.OutDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepo extends JpaRepository<Coursestudent, MyTableId> {
    @Query(value = "SELECT co.coursename FROM course co join coursestudent cs on cs.courseid = co.courseid where cs.uid= 30", nativeQuery = true)
        List<OutDTO> findCourseNameByUserId();

}