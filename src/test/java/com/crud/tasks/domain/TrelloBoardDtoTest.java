package com.crud.tasks.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TrelloBoardDtoTest {

    @Test
    void trelloBoardDtoTestWithNoArgsConstructor() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();

        //When

        //Then
        Assertions.assertNull(trelloBoardDto.getId());
        Assertions.assertNull(trelloBoardDto.getName());
        Assertions.assertNull(trelloBoardDto.getLists());
    }

    @Test
    void trelloBoardDtoTestWithAllArgsConstructor() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "board name", new ArrayList<>());

        //When

        //Then
        Assertions.assertEquals("board name", trelloBoardDto.getName());
        Assertions.assertEquals("1", trelloBoardDto.getId());
        Assertions.assertTrue(trelloBoardDto.getLists().isEmpty());
    }
}
