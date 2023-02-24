package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByName(String name);
    @Query(value = "SELECT * FROM user WHERE role = 1", nativeQuery = true)
    List<User> findAllStudent(String role);





}
