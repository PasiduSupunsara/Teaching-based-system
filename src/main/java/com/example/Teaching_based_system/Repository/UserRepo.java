package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.name = ?1")
    User findByName(String name);





}
