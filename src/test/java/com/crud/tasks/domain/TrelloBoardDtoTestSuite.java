package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TrelloBoardDtoTestSuite {

    @Test
    void NoArgsConstructor() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();

        //When
        trelloBoardDto.setId("id");
        trelloBoardDto.setName("name");
        trelloBoardDto.setLists(new ArrayList<>());

        //Then
        assertEquals("id",trelloBoardDto.getId());
        assertEquals(0, trelloBoardDto.getLists().size());
    }
}
