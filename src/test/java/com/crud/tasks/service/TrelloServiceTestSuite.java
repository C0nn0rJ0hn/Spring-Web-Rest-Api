package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService service;

    @Mock
    private TrelloClient client;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig config;

    @Test
    void shouldFetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> boardList = new ArrayList<>();
        boardList.add(new TrelloBoardDto("1234", "Name", new ArrayList<>()));
        boardList.add(new TrelloBoardDto("3214", "Name2", new ArrayList<>()));

        when(client.getTrelloBoards()).thenReturn(boardList);

        //When
        List<TrelloBoardDto> boardDtos = service.fetchTrelloBoards();

        //Then
        assertEquals(2, boardDtos.size());
        assertEquals("Name", boardDtos.get(0).getName());
    }

    @Test
    void shouldCreateTrelloCard() {
        //Given
        TrelloCardDto card = new TrelloCardDto("Name", "Desc", "top", "12345");

        when(client.createNewCard(card)).thenReturn(new CreatedTrelloCardDto("ID", "NAME", "URL"));

        //When
        CreatedTrelloCardDto cardDto = service.createTrelloCard(card);

        //Then
        assertEquals("NAME", cardDto.getName());
    }
}
