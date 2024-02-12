package com.aciojob.LibraryManagementSystem.Services;

import com.aciojob.LibraryManagementSystem.Entities.Student;
import com.aciojob.LibraryManagementSystem.Repository.Studentrepository;
import com.aciojob.LibraryManagementSystem.RequestDTOs.ModifyPhoneNoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServive {

    @Autowired
    private Studentrepository studentrepository;

    //Student entity contains business related logic part
    public String addStudent(Student student)
    {
        Student savedStudent = studentrepository.save(student);

        return "Student has been saved to DB with rollNo.: "+savedStudent.getStudentId();
    }

    public Student findStudentById(Integer studentId) throws Exception //optional return type
    {
        Optional<Student> optionalStudent = studentrepository.findById(studentId);

        if(optionalStudent.isEmpty())
        {
            throw new Exception("studentId entered is Incorrect!!!");
        }

        Student student = optionalStudent.get();

        return student;
    }

    public String ModifyPhoneNo(ModifyPhoneNoRequest modifyPhoneNoRequest)
    {
        Integer studentId = modifyPhoneNoRequest.getStudentId();
        String newPhoneNo = modifyPhoneNoRequest.getNewPhoneNo();

        Student student = studentrepository.findById(studentId).get();
        student.setPhone_number(newPhoneNo);
        studentrepository.save(student);

        return "Phone Number has been updated!!!";
    }
}
