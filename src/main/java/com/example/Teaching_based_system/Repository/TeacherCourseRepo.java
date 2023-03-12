package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.Course;
import com.example.Teaching_based_system.Entity.Courseteacher;
import com.example.Teaching_based_system.Entity.TeacherPrimary;
import com.example.Teaching_based_system.ResponseDTO.Out1DTO;
import com.example.Teaching_based_system.ResponseDTO.Out2DTO;
import com.example.Teaching_based_system.ResponseDTO.Out3DTO;
import com.example.Teaching_based_system.ResponseDTO.OutDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCourseRepo extends JpaRepository<Courseteacher, TeacherPrimary> {
    @Query(value = "SELECT coursename FROM course", nativeQuery = true)
    List<OutDTO> findCourseNameByUserIdForTeacher();

    @Query(value = "SELECT ct.tid FROM coursestudent cs join courseteacher ct on cs.courseid = ct.courseid where cs.sid = ?1", nativeQuery = true)
    List<Out1DTO> findTeacherId(int sid);

    @Query(value = "SELECT u.name FROM coursestudent cs join courseteacher ct on cs.courseid = ct.courseid join user u on u.id = ct.tid where cs.sid = ?1", nativeQuery = true)
    List<Out2DTO> findTeacherName(int sid);

    @Query(value = "SELECT COUNT(*) FROM courseteacher WHERE tid =?1 AND courseid = ?2 ",nativeQuery = true)
    int CountCourseTeacher(int tid,int courseid);

    @Query(value = "select tid from courseteacher where courseid = ?1",nativeQuery = true)
    List<Integer> teacherByCourseId(int courseid);




}
