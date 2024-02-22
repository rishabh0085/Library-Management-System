package com.aciojob.LibraryManagementSystem.Entities;
import com.aciojob.LibraryManagementSystem.Enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //bookId should be System Generated value
    private int bookId;

    @Column(unique = true) //bookName entered should be UNIQUE
    private String bookName;

    @Enumerated(value = EnumType.STRING)
    private Genre genre; //genre is type of enum.

    private int noOfPages;

    private int price;

    private Date publishingDate;

    private Boolean isAvailable; //To check if book is available or not

    //Connecting Book Entity with Author Entity
    //Author Parent and Book is Child
    //Foreign key will be in Book
    @JoinColumn
    @JsonIgnore
    @ManyToOne
    private Author author;

    //Bi-Directional mapping b/w Book and Transaction
    //transaction is child and book is parent
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    public List<Transaction> transactionList = new ArrayList<>();

    public Book(String bookName, Genre genre, Boolean isAvailable, int noOfPages, int price, Date publishingDate) {
        this.bookName = bookName;
        this.genre = genre;
        this.isAvailable = isAvailable;
        this.noOfPages = noOfPages;
        this.price = price;
        this.publishingDate = publishingDate;
    }
}
