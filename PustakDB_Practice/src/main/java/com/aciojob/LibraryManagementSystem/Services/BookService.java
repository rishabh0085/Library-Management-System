package com.aciojob.LibraryManagementSystem.Services;

import com.aciojob.LibraryManagementSystem.Entities.Author;
import com.aciojob.LibraryManagementSystem.Entities.Book;
import com.aciojob.LibraryManagementSystem.Repository.AuthorRepository;
import com.aciojob.LibraryManagementSystem.Repository.BookRepository;
import com.aciojob.LibraryManagementSystem.RequestDTOs.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(AddBookRequest addBookRequest)
    {
        //Logical steps :
        //1. get AuthorEntity from authorId
        Author author = authorRepository.findById(addBookRequest.getAuthorId()).get();

        //2. create Book Entity from Book Request
        Book newbook = new Book(
                addBookRequest.getBookName(),addBookRequest.getGenre(),
                addBookRequest.getIsAvailable(),
                addBookRequest.getNoOfPages(),addBookRequest.getPrice()
                ,addBookRequest.getPublishingDate());

        //we need to increment noOfBooksWritten by author in Author Entity
        author.setNoOfBooksWritten(author.getNoOfBooksWritten()+1);

        //3. set F.K variable....Bi-Directional mapping
        //In Book Entity u need to save the Author Entity...Book is child and Author is parent entity

        //3.1. Adding for the book the author entity
        newbook.setAuthor(author); //Uni-Directional Mapping

        //3.2. for the author add the book in the bookList
        author.getBookList().add(newbook); //Bi-Directional Mapping

        //4.Save the Parent Class
        authorRepository.save(author);

        return "New book has been added to DB!!!";

    }

    public Book findBookById(Integer bookId) throws Exception
    {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isEmpty())
        {
            throw new Exception("BookId is Invalid!!!");
        }

        Book book = optionalBook.get();

        return book;
    }

    public List<String> retrieveAllBooks()
    {
        List<String> allBookNames = bookRepository.findBookNames();
        return allBookNames;
    }

    public String deleteBook(Integer bookId) throws Exception
    {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isEmpty())
        {
            throw new Exception("BookId is Invalid!!!");
        }

        Book book = optionalBook.get();

        bookRepository.delete(book);

        return "Book with bookId "+bookId+" has been deleted successfully!!!";
    }
}
