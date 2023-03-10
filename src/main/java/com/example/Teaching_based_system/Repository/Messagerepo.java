package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.Message;
import com.example.Teaching_based_system.ResponseDTO.ViewUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Messagerepo extends JpaRepository<Message,Integer> {
    @Query(value = "SELECT * FROM message where rid = ?1",nativeQuery = true)
    List<Message> findAllByRid(int rid);
}
