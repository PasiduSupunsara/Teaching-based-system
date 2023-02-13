package com.example.Teaching_based_system.Repository;


import com.example.Teaching_based_system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByName(String username);


}
