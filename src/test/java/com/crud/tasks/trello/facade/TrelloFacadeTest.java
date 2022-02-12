package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    void shouldFetchEmptyList() {
        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(List.of());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(List.of());

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        Assertions.assertThat(trelloBoardDtos).isNotNull();
        Assertions.assertThat(trelloBoardDtos.size()).isEqualTo(0);
    }

    @Test
    void shouldFetchTrelloBoards() {
        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        // When
        List<TrelloBoardDto> trelloBoardDto = trelloFacade.fetchTrelloBoards();

        // Then
        Assertions.assertThat(trelloBoardDto).isNotNull();
        Assertions.assertThat(trelloBoardDto.size()).isEqualTo(1);

        trelloBoardDto.forEach(trelloBoardDtos -> {

            Assertions.assertThat(trelloBoardDtos.getId()).isEqualTo("1");
            Assertions.assertThat(trelloBoardDtos.getName()).isEqualTo("test");

            trelloBoardDtos.getLists().forEach(trelloListDto -> {
                Assertions.assertThat(trelloListDto.getId()).isEqualTo("1");
                Assertions.assertThat(trelloListDto.getName()).isEqualTo("test_list");
                Assertions.assertThat(trelloListDto.isClosed()).isFalse();
            });
        });
    }

    @Test
    void createCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        trelloCardDto.setName("card name");
        trelloCardDto.setDescription("card description");
        trelloCardDto.setPos("card pos");
        trelloCardDto.setListId("1");

        TrelloCard trelloCard = new TrelloCard("card name", "card description", "card pos", "1");

        CreatedTrelloCardDto createdTrelloCardDtoMock = new CreatedTrelloCardDto();
        createdTrelloCardDtoMock.setId("1");
        createdTrelloCardDtoMock.setName("card name");
        createdTrelloCardDtoMock.setShortUrl("123");

        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDtoMock);

        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloFacade.createCard(trelloCardDto);

        //Then
        Assertions.assertThat(createdTrelloCardDto).isNotNull();
        Assertions.assertThat(createdTrelloCardDto.getId()).isEqualTo("1");
        Assertions.assertThat(createdTrelloCardDto.getName()).isEqualTo("card name");
        Assertions.assertThat(createdTrelloCardDto.getShortUrl()).isEqualTo("123");
    }
}