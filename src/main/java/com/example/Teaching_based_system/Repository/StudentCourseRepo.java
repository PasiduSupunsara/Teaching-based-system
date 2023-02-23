package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.Coursestudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepo extends JpaRepository<Coursestudent,Integer> {
//    @Query(value = "SELECT id FROM user", nativeQuery = true)
//    List<Out> findProduct();

}
