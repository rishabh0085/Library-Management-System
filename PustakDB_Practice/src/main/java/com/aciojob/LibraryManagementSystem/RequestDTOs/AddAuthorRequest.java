package com.aciojob.LibraryManagementSystem.RequestDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAuthorRequest {

    private String author_name;

    private int authorAge;

    private String emailId;
}
