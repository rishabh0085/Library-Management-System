package com.aciojob.LibraryManagementSystem.Controllers;

import com.aciojob.LibraryManagementSystem.Entities.Student;
import com.aciojob.LibraryManagementSystem.RequestDTOs.ModifyPhoneNoRequest;
import com.aciojob.LibraryManagementSystem.Services.StudentServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServive studentServive;

    //All Student related APIs will be written here
    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody Student student)
    {
        String result = studentServive.addStudent(student);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    public ResponseEntity findStudentById(@RequestParam("studentId") Integer studentId)
    {
        try
        {
            Student student = studentServive.findStudentById(studentId);
            return new ResponseEntity(student,HttpStatus.OK);
        }
        catch (Exception e)
        {
             return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updatePhoneNo")
    public String ModifyPhoneNo(@RequestBody ModifyPhoneNoRequest modifyPhoneNoRequest)
    {
         String result = studentServive.ModifyPhoneNo(modifyPhoneNoRequest);
         return result;
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity deleteStudent(@RequestParam("studentId") Integer studentId)
    {
        try
        {
            String result = studentServive.deleteStudent(studentId);
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
