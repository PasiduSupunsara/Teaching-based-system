package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.Assesment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssesmentRepo extends JpaRepository<Assesment,Integer> {
    @Query(value = "select * from assesment where cid = ?1",nativeQuery = true)
    List<Assesment> getAllAssesmentByCid(int cid);

}
