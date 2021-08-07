package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BadgesTestSuite {

    @Test
    void BadgesNoArgsConstructorTest() {
        //Given
        Badges badges = new Badges();
        AttachmentsByType attachments = new AttachmentsByType();

        //When
        badges.setVotes(50);
        badges.setAttachmentsByType(attachments);

        //Then
        assertEquals(50, badges.getVotes());
    }
}
