package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void mapToBoardsTest() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "board one", new ArrayList<>()));
        trelloBoardDtos.add(new TrelloBoardDto("2", "board two", new ArrayList<>()));
        trelloBoardDtos.add(new TrelloBoardDto("3", "board three", new ArrayList<>()));
        trelloBoardDtos.add(new TrelloBoardDto("4", "board four", new ArrayList<>()));

        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals("board one", result.get(0).getName());
        Assertions.assertTrue(result.get(0).getLists().isEmpty());

    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "board one", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2", "board two", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("3", "board three", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("4", "board four", new ArrayList<>()));

        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals("board one", result.get(0).getName());
        Assertions.assertTrue(result.get(0).getLists().isEmpty());
    }

    @Test
    void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "list one", false));
        trelloListDtos.add(new TrelloListDto("2", "list two", true));
        trelloListDtos.add(new TrelloListDto("3", "list three", false));
        trelloListDtos.add(new TrelloListDto("4", "list four", true));

        //When
        List<TrelloList> result = trelloMapper.mapToList(trelloListDtos);;

        //Then
        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals("list one", result.get(0).getName());
        Assertions.assertFalse(result.get(0).isClosed());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "list one", false));
        trelloLists.add(new TrelloList("2", "list two", true));
        trelloLists.add(new TrelloList("1", "list three", false));
        trelloLists.add(new TrelloList("2", "list four", true));

        //When
        List<TrelloListDto> result = trelloMapper.mapToListDto(trelloLists);

        //Then
        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals("list one", result.get(0).getName());
        Assertions.assertFalse(result.get(0).isClosed());
    }

    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card", "card description", "card pos", "1");

        //When
        TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assertions.assertEquals("card", result.getName());
        Assertions.assertEquals("card description", result.getDescription());
        Assertions.assertEquals("card pos", result.getPos());
        Assertions.assertEquals("1", result.getListId());
    }

    @Test
    void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "card description", "card pos", "1");

        //When
        TrelloCard result = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assertions.assertEquals("card", result.getName());
        Assertions.assertEquals("card description", result.getDescription());
        Assertions.assertEquals("card pos", result.getPos());
        Assertions.assertEquals("1", result.getListId());
    }
}
