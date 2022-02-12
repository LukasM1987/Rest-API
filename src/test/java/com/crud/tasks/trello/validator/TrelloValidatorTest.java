package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    void validateTrelloBoardsTest() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "board name", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2", "board name", new ArrayList<>()));

        //When
        List<TrelloBoard> result = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("1", result.get(0).getId());
        Assertions.assertEquals("board name", result.get(1).getName());
        Assertions.assertTrue(result.get(0).getLists().isEmpty());
    }

    @Test
    void emptyListTest() {
        //Given
        TrelloBoard testTrelloBoard = new TrelloBoard("test id", "test", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(testTrelloBoard);

        //When
        List<TrelloBoard> result = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void validateCardTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card name", "description", "card pos", "1");

        //When & Then
        trelloValidator.validateCard(trelloCard);
    }
}
