package com.aciojob.LibraryManagementSystem.Controllers;

import com.aciojob.LibraryManagementSystem.Entities.Book;
import com.aciojob.LibraryManagementSystem.RequestDTOs.AddBookRequest;
import com.aciojob.LibraryManagementSystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public String addBook(@RequestBody AddBookRequest addBookRequest)
    {
        String result = bookService.addBook(addBookRequest);
        return result;
    }

    @GetMapping("/findBookById")
    public ResponseEntity findBookById(@RequestParam("bookId") Integer bookId)
    {
        try
        {
            Book book = bookService.findBookById(bookId);
            return new ResponseEntity(book, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("retrieveAllBooks")
    public ResponseEntity retrieveAllBooks() {
        try {
            List<String> result = bookService.retrieveAllBooks();
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteBook")
    public ResponseEntity deleteBook(@RequestParam("bookId") Integer bookId)
    {
        try
        {
           String result = bookService.deleteBook(bookId);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
