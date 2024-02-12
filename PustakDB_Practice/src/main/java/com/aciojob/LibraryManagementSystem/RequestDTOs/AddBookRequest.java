package com.aciojob.LibraryManagementSystem.RequestDTOs;

import com.aciojob.LibraryManagementSystem.Enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddBookRequest {

    private String bookName;

    private Genre genre;

    private Boolean isAvailable;

    private int noOfPages;

    private int price;

    private Date publishingDate;

    private Integer authorId;
}
