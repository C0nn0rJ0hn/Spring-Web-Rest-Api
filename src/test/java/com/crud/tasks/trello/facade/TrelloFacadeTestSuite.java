package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloFacadeTestSuite {

    @Autowired
    private TrelloMapper mapper;

    @Test
    void testMapToBoards() {
        //Given
        List<TrelloBoardDto> dtoList = new ArrayList<>();
        TrelloBoardDto boardDto = new TrelloBoardDto("1", "DTO Board", new ArrayList<>());
        dtoList.add(boardDto);

        //When
        List<TrelloBoard> resultList = mapper.mapToBoards(dtoList);

        //Then
        assertEquals(1, resultList.size());
        assertEquals("DTO Board", resultList.get(0).getName());
    }

    @Test
    void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(new TrelloBoard("1", "Board", new ArrayList<>()));

        //When
        List<TrelloBoardDto> resultList = mapper.mapToBoardsDto(boardList);

        //Then
        assertEquals(1, resultList.size());
        assertEquals("1", resultList.get(0).getId());
    }

    @Test
    void testMapToList() {
        //Given
        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(new TrelloListDto("1", "List DTO", true));

        //When
        List<TrelloList> resultList = mapper.mapToList(listDto);

        //Then
        assertEquals(1, resultList.size());
        assertTrue(resultList.get(0).isClosed());
    }

    @Test
    void testMapToListDto() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("1", "List", false));

        //When
        List<TrelloListDto> resultList = mapper.mapToListDto(list);

        //Then
        assertEquals(1, resultList.size());
        assertFalse(resultList.get(0).isClosed());
    }

    @Test
    void testMapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("Trello Card DTO", "New Card DTO", "bottom", "12345677");

        //When
        TrelloCard card = mapper.mapToCard(cardDto);

        //Then
        assertEquals("bottom", card.getPos());
        assertEquals("Trello Card DTO", card.getName());
    }

    @Test
    void testMapToCardDto() {
        //Given
        TrelloList list = new TrelloList("1", "Trello List", false);
        TrelloCard card = new TrelloCard("Trello Card", "New Card", "top", list.getId());

        //When
        TrelloCardDto cardDto = mapper.mapToCardDto(card);

        //Then
        assertEquals("1", cardDto.getListId());
        assertEquals("Trello Card", cardDto.getName());
    }
}
