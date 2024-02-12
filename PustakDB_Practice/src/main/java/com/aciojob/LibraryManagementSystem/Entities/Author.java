package com.aciojob.LibraryManagementSystem.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    private String author_name;

    private int authorAge;

    @Column(unique = true,nullable = false)
    private String emailId;

    private int NoOfBooksWritten;

    //Bi-directional Mapping between Book and Author Entity
    @JsonIgnore
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();

    public Author(String author_name, int authorAge, String emailId) {
        this.author_name = author_name;
        this.authorAge = authorAge;
        this.emailId = emailId;
    }
}
