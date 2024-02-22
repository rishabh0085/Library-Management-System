package com.aciojob.LibraryManagementSystem.Services;

import com.aciojob.LibraryManagementSystem.Entities.Book;
import com.aciojob.LibraryManagementSystem.Entities.LibraryCard;
import com.aciojob.LibraryManagementSystem.Entities.Student;
import com.aciojob.LibraryManagementSystem.Entities.Transaction;
import com.aciojob.LibraryManagementSystem.Enums.TransactionStatus;
import com.aciojob.LibraryManagementSystem.Enums.TransactionType;
import com.aciojob.LibraryManagementSystem.Exceptions.BookNotAvailableException;
import com.aciojob.LibraryManagementSystem.Exceptions.BookNotFoundException;
import com.aciojob.LibraryManagementSystem.Exceptions.CardNotFoundException;
import com.aciojob.LibraryManagementSystem.Exceptions.MAXLimitREACHEDException;
import com.aciojob.LibraryManagementSystem.Repository.BookRepository;
import com.aciojob.LibraryManagementSystem.Repository.Cardrepository;
import com.aciojob.LibraryManagementSystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private Cardrepository cardRepository;

    public String issueBook(Integer cardId, Integer bookId) throws Exception
    {
        //Logical steps to issue a book...

        //create new Transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.ISSUED);
        transaction.setTransactionStatus(TransactionStatus.ONGOING);

        //1. get book and libraryCard entity from DB
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);

        if(optionalBook.isEmpty())
        {
            throw new BookNotFoundException("Book ID entered is Invalid!!!");
        }

        if (optionalLibraryCard.isEmpty())
        {
            throw new CardNotFoundException("Library_Card entered is Invalid!!!");
        }

        Book book = optionalBook.get();
        LibraryCard card = optionalLibraryCard.get();

        //2. we need to check weather book isAvailable or Not....if available then set availability to FALSE!!!
        if(book.getIsAvailable() == Boolean.FALSE)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction = transactionRepository.save(transaction);
            throw new BookNotAvailableException("Book with bookId is not available. TransactionId "+transaction.getTransactionId());
        }

        //3. check max books issued!!!
        if(card.getNoOfBooksIssued() >= LibraryCard.MAX_NO_OF_ALLOWED_BOOKS)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction = transactionRepository.save(transaction);
            throw new MAXLimitREACHEDException("You have reached the max limit of books issued." +
                    "Please return a book in order to get new book issued!!!"
            + " Transaction ID " +transaction.getTransactionId());
        }

        //If you reached here means all validations are OK
        //Set transactionStatus to SUCCESS
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionType(TransactionType.ISSUED);
        transaction.setFineAmount(0);
        transaction.setCreatedOn(LocalDateTime.now());
        transaction.setFineAmount(transaction.getFineAmount());

        //4.Update Card and Book Status
        book.setIsAvailable(Boolean.FALSE);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()+1);

        //Child class also need to have attributes of the Parent Class
        transaction.setBook(book);
        transaction.setLibraryCard(card);

        //5.save the transaction
        //save the child as it will cascade to both the parents
        transaction = transactionRepository.save(transaction);
        return "The Transaction wit Id: "+transaction.getTransactionId()+" has been saved to DB!!!";
    }

    public String returnBook(Integer cardId,Integer bookId) throws Exception
    {
        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.ONGOING);
        transaction.setTransactionType(TransactionType.RETURN);
        transaction.setCreatedOn(LocalDateTime.now());
        Optional<Book> OptionalBook = bookRepository.findById(bookId);
        if (OptionalBook.isEmpty()) {
            throw new Exception("Book ID is Invalid");
        }
        Book book = OptionalBook.get();
        Optional<LibraryCard> OptionalCard = cardRepository.findById(cardId);
        if (OptionalBook.isEmpty()) {
            throw new Exception("Invalid card ID");
        }
        LibraryCard card = OptionalCard.get();
        List<Transaction> transactionList=card.getTransactionList();
        Transaction issueTransaction=null;
        for(Transaction t:transactionList)
        {
            if(t.getBook().getBookId()==bookId)
            {
                //System.out.println(t.getBook().getBookName());
                issueTransaction=t;
            }
        }
        if(issueTransaction==null)
        {
            throw new Exception("THIS IS NOT ALLOTED TO YOU!!!");
        }
        transaction.setBook(book);
        transaction.setLibraryCard(card);
        //so at end we will have latest transaction  for  this book against  this card
        LocalDateTime issueDate= issueTransaction.getCreatedOn();
        LocalDateTime ReturndDate= transaction.getCreatedOn();
        Period p=Period.between(issueDate.toLocalDate(),ReturndDate.toLocalDate());
        int Days=p.getDays();
        if(Days>20)
        {
            int fine= (Days-20)*100;
            transaction.setFineAmount(fine);
        }
        book.setIsAvailable(Boolean.TRUE);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);
        return "BOOK IS SUBMITTED WITH FINE AMOUNT OF RS. "+transaction.getFineAmount();

    }

}
