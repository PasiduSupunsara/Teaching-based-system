package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepo extends JpaRepository<Token,String> {

}
