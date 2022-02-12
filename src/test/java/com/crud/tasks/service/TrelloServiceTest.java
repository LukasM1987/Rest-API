package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    void fetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDtos =
                List.of(new TrelloListDto("1", "list one", true));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "board one", trelloListDtos));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        Assertions.assertNotNull(trelloBoardDtos);
        Assertions.assertEquals("board one", trelloService.fetchTrelloBoards().get(0).getName());
    }
}
