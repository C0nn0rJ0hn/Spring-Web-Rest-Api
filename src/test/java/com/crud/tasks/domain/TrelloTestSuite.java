package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrelloTestSuite {

    @Test
    void TrelloNoArgsConstructor() {
        //Given
        Trello trello = new Trello();

        //When
        trello.setBoard(1);
        trello.setCard(5);

        //Then
        assertEquals(1, trello.getBoard());
        assertEquals(5, trello.getCard());
    }
}
