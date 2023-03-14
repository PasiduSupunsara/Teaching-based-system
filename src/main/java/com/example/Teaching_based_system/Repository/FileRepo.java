package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepo extends JpaRepository<File,Integer> {
    @Query(value = "select * from file where cid = ?1 and assid = ?2 and sid = ?3",nativeQuery = true)
    Optional<File> findByCourseid(int courseid,int assid,int sid);

    @Query(value = "select sid from file where cid = ?1 and assid = ?2",nativeQuery = true)
    List<Integer> findSubmissions(int courseid,int assid);

}
