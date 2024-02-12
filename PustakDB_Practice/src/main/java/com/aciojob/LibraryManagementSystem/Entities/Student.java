package com.aciojob.LibraryManagementSystem.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId; //PrimaryKey for student table

    private String name;

    private String branch;

    private String phone_number;

    @JsonIgnore
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL) //mappedBy contains the value of variable name: F.K variable name in the child table
    private LibraryCard libraryCard;
}
