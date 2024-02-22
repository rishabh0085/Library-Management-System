package com.aciojob.LibraryManagementSystem.Repository;

import com.aciojob.LibraryManagementSystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT book_name from book", nativeQuery = true)
    List<String> findBookNames();
}
