package com.example.Teaching_based_system.Repository;

import com.example.Teaching_based_system.Entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<File,Integer> {
}
