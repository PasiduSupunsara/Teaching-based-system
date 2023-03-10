package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.Assesment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AssesmentRepo extends JpaRepository<Assesment,Integer> {
    @Query(value = "select * from assesment where cid = ?1",nativeQuery = true)
    List<Assesment> getAllAssesmentByCid(int cid);

    @Query(value = "SELECT ass.* from assesment ass join coursestudent cs on cs.courseid = ass.cid where ass.duedate <= ?1 and ass.duedate >= ?2 and cs.sid = ?3",nativeQuery = true)
    List<Assesment> getTimeLine(LocalDate date1,LocalDate date2,int sid);

    @Query(value = "SELECT ass.* from assesment ass join courseteacher ct on ct.courseid = ass.cid where ass.duedate <= ?1 and ass.duedate >= ?2 and ct.tid = ?3",nativeQuery = true)
    List<Assesment> getTimeLineForTeacher(LocalDate date1,LocalDate date2,int tid);

}
