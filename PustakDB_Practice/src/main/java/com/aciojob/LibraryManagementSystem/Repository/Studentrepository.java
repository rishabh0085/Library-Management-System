package com.aciojob.LibraryManagementSystem.Repository;

import com.aciojob.LibraryManagementSystem.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Studentrepository extends JpaRepository<Student,Integer> {

}
