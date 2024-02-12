package com.aciojob.LibraryManagementSystem.Entities;

import com.aciojob.LibraryManagementSystem.Enums.CardStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "library_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {

    public static final Integer MAX_NO_OF_ALLOWED_BOOKS = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    //CardStatus is of type enum data-type
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) //This annotation is used to tell JPA ki cardStatus is of type enum
    private CardStatus cardStatus;

    private int noOfBooksIssued;

    //Library Card is child of Student
    //Foreign key will be at Library_card
    @JoinColumn
    @OneToOne    //i want one to one mapping
    @JsonIgnore
    private Student student;

    private double fine; //store fine amount

    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL) //Bi-Directional mapping between Transaction table and LC
    public List<Transaction> transactionList = new ArrayList<>();

    private Date issueDate;

    public void setFine(double fine) {
        this.fine = fine;
    }
}
