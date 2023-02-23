package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.Courseteacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCourseRepo extends JpaRepository<Courseteacher,Integer> {
//    @Query(value = "SELECT id FROM ct", nativeQuery = true)
//    List<Out> findProduct();
}
