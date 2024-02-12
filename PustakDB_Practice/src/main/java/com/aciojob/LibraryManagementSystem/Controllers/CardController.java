package com.aciojob.LibraryManagementSystem.Controllers;

import com.aciojob.LibraryManagementSystem.RequestDTOs.AssociateCardStudentRequest;
import com.aciojob.LibraryManagementSystem.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;
    @PostMapping("/generateACard")
    public String generateACard()
    {
         String result = cardService.getFreshCard();
         return result;
    }

    @PostMapping("/associateCardWithStudent")
    public ResponseEntity associateCardWithStudent(AssociateCardStudentRequest associateCardStudentRequest) //or use can use: public ResponseEntity associateCardWithStudent(@RequestParam("studentId") Integer studentId, @RequestParam("cardId") Integer cardId
    {
        try {

            //String result = cardService.associateCardWithStudent(studentId, cardId);
            String result = cardService.associateCardWithStudent(associateCardStudentRequest);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
