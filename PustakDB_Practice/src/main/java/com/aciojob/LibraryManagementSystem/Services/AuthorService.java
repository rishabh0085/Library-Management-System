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

    public Author updateAuthor(Integer authorId, Author updateAuthor) throws Exception
    {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if (optionalAuthor.isEmpty()) {
            throw new Exception("AuthorId is invalid!!!");
        }

        Author existingAuthor = optionalAuthor.get();

        existingAuthor.setAuthor_name(updateAuthor.getAuthor_name());
        existingAuthor.setAuthorAge(updateAuthor.getAuthorAge());
        existingAuthor.setEmailId(updateAuthor.getEmailId());

        authorRepository.save(existingAuthor);

        return existingAuthor;

    }

    public String deleteAuthor(Integer authorId) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if (optionalAuthor.isEmpty()) {
            throw new Exception("AuthorId is invalid!!!");
        }

        Author author = optionalAuthor.get();

        authorRepository.delete(author);

        return "Atuhor with "+authorId+" has been deleted successfully!!!";
    }


}
