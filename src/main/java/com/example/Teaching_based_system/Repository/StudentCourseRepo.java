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
    @Query(value = "SELECT co.coursename FROM course co join coursestudent cs on cs.courseid = co.courseid where cs.sid= ?1", nativeQuery = true)
        List<OutDTO> findCourseNameByUserId(int sid);

    @Query(value = "select count(sid) from coursestudent where courseid = ?1",nativeQuery = true)
        int countStudentByCourseId(int courseid);

    @Query(value = "SELECT COUNT(*) FROM coursestudent WHERE sid =?1 AND courseid = ?2 ",nativeQuery = true)
    int CountCourseStudent(int sid,int courseid);

}
