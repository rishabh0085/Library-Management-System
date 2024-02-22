package com.aciojob.LibraryManagementSystem.Repository;

import com.aciojob.LibraryManagementSystem.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {

}
