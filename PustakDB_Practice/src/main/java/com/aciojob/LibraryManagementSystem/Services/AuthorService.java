package com.aciojob.LibraryManagementSystem.Services;

import com.aciojob.LibraryManagementSystem.Entities.Author;
import com.aciojob.LibraryManagementSystem.Repository.AuthorRepository;
import com.aciojob.LibraryManagementSystem.RequestDTOs.AddAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private JavaMailSender mailSender;
    public String addAuthor(AddAuthorRequest addAuthorRequest)
    {
        Author authorEntity = new Author(addAuthorRequest.getAuthor_name(),addAuthorRequest.getAuthorAge(),addAuthorRequest.getEmailId());
        Author newAuthor = authorRepository.save(authorEntity);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Hi"+newAuthor.getAuthor_name());
        mailMessage.setFrom("springacciojob@gmail.com");
        mailMessage.setTo(newAuthor.getEmailId());

        mailMessage.setText("You have been successfully registered on our portal!!!"+
                " Looking forward for adding more books!!!");

        mailSender.send(mailMessage);

        return "Author with authorId "+newAuthor.getAuthorId()+" has been saved to DB";
    }

    public Author findAuthorById(Integer authorId) throws Exception
    {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if(optionalAuthor.isEmpty())
        {
            throw new Exception("AuthorId is invalid!!!");
        }

        Author author = optionalAuthor.get();
        return  author;
    }

}
