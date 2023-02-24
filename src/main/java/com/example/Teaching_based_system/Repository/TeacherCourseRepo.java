package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.Courseteacher;
import com.example.Teaching_based_system.Entity.TeacherPrimary;
import com.example.Teaching_based_system.ResponseDTO.OutDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCourseRepo extends JpaRepository<Courseteacher, TeacherPrimary> {
    @Query(value = "SELECT coursename FROM course", nativeQuery = true)
    List<OutDTO> findCourseNameByUserIdForTeacher();
}
