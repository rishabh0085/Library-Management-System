package com.aciojob.LibraryManagementSystem.Controllers;

import com.aciojob.LibraryManagementSystem.Entities.Author;
import com.aciojob.LibraryManagementSystem.RequestDTOs.AddAuthorRequest;
import com.aciojob.LibraryManagementSystem.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public String addAuthor(@RequestBody AddAuthorRequest addAuthorRequest)
    {
        String result = authorService.addAuthor(addAuthorRequest);
        return result;
    }

    @GetMapping("/findAuthorById")
    public ResponseEntity findAuthorById(@RequestParam("authorId") Integer authorId)
    {
        try
        {
            Author author = authorService.findAuthorById(authorId);
            return new ResponseEntity(author, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateAuthor")
    public ResponseEntity updateAuthor(@RequestParam("authorId") Integer authorId,@RequestBody Author updatedAuthor)
    {
        try
        {
            Author updatedAuthorInfo = authorService.updateAuthor(authorId,updatedAuthor);
            return new ResponseEntity(updatedAuthorInfo, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAuthor")
    public ResponseEntity deleteAuthor(@RequestParam("authorId") Integer authorId)
    {
        try
        {
            String result = authorService.deleteAuthor(authorId);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
