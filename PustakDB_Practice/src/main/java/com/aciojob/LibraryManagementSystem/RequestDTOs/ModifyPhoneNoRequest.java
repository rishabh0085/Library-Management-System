package com.aciojob.LibraryManagementSystem.RequestDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyPhoneNoRequest {

    private Integer studentId;
    private String newPhoneNo;
}
