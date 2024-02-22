package com.aciojob.LibraryManagementSystem.Services;

import com.aciojob.LibraryManagementSystem.Entities.LibraryCard;
import com.aciojob.LibraryManagementSystem.Entities.Student;
import com.aciojob.LibraryManagementSystem.Enums.CardStatus;
import com.aciojob.LibraryManagementSystem.Repository.Cardrepository;
import com.aciojob.LibraryManagementSystem.Repository.Studentrepository;
import com.aciojob.LibraryManagementSystem.RequestDTOs.AssociateCardStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private Cardrepository cardrepository;

    @Autowired
    private Studentrepository studentrepository;
    public String getFreshCard()
    {
        LibraryCard newCard = new LibraryCard(); //we create newCard
        newCard.setCardStatus(CardStatus.NEW); //set status of card to NEW
        newCard.setNoOfBooksIssued(0); //NoOfBooks issued to newCard is 0

        LibraryCard savedCard = cardrepository.save(newCard); //CardRepository may save newCard

        return "New card with card no.: "+savedCard.getCardId()+" has been generated";
    }

    public String associateCardWithStudent(AssociateCardStudentRequest associateCardStudentRequest) throws Exception
    {
        Integer studentId = associateCardStudentRequest.getStudentId();
        Integer cardId = associateCardStudentRequest.getCardId();

        Optional<LibraryCard> optionalLibraryCard = cardrepository.findById(cardId);

        if(optionalLibraryCard.isEmpty())
        {
            throw new Exception("Invalid card Id entered!!!");
        }

        LibraryCard libraryCard = optionalLibraryCard.get();

        Optional<Student> optionalStudent = studentrepository.findById(studentId);

        if(optionalStudent.isEmpty())
        {
            throw new Exception("Invalid Student Id entered!!!");
        }

        Student student = optionalStudent.get();

        //i need to update card repository
        //Add Changes to RepoDB before that i need to set entity attributes

        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);
        libraryCard.setNoOfBooksIssued(0);

        cardrepository.save(libraryCard);
        return "Card with cardId "+cardId+" Student with studentId "+studentId+" are associated";
    }
}
