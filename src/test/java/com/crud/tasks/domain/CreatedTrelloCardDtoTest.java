package com.crud.tasks.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreatedTrelloCardDtoTest {

    @Test
    void createdTrelloCardWithNoArgsConstructorTest() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();

        //When

        //Then
        Assertions.assertNull(createdTrelloCardDto.getId());
        Assertions.assertNull(createdTrelloCardDto.getName());
        Assertions.assertNull(createdTrelloCardDto.getShortUrl());
    }

    @Test
    void createdTrelloCardWithAllArgsConstructorTest() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "card one", "123");

        //When

        //Then
        Assertions.assertEquals("1", createdTrelloCardDto.getId());
        Assertions.assertEquals("card one", createdTrelloCardDto.getName());
        Assertions.assertEquals("123", createdTrelloCardDto.getShortUrl());
    }

    @Test
    void createdTrelloCardWithSettersTest() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "card one", "123");

        //When
        createdTrelloCardDto.setId("2");
        createdTrelloCardDto.setName("card two");
        createdTrelloCardDto.setShortUrl("321");

        //Then
        Assertions.assertEquals("2", createdTrelloCardDto.getId());
        Assertions.assertEquals("card two", createdTrelloCardDto.getName());
        Assertions.assertEquals("321", createdTrelloCardDto.getShortUrl());
    }
}
