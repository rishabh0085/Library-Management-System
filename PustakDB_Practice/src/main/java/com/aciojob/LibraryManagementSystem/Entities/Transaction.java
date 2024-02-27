package com.aciojob.LibraryManagementSystem.Entities;
import com.aciojob.LibraryManagementSystem.Enums.TransactionStatus;
import com.aciojob.LibraryManagementSystem.Enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreatedDate
    private LocalDateTime createdOn;//Handled by Spring internally

    private Date returnDate;

    private Integer fineAmount = 0;

    public Transaction(TransactionStatus transactionStatus, TransactionType transactionType, Integer fineAmount) {
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.fineAmount = fineAmount;
    }

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    //establish the connection b/w LibraryCard and Transaction table
    //LC is Parent class and Transaction table is child class
    @JsonIgnore
    @JoinColumn
    @ManyToOne
    private LibraryCard libraryCard;

    //Establish the connection b/w transaction and Book table
    @JsonIgnore
    @JoinColumn
    @ManyToOne
    private Book book;

}
