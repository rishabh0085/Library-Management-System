package com.aciojob.LibraryManagementSystem.Repository;

import com.aciojob.LibraryManagementSystem.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cardrepository extends JpaRepository<LibraryCard,Integer> {
}
