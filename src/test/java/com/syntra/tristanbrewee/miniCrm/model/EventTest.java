package com.syntra.tristanbrewee.miniCrm.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    private Event event;
    private Integer eventId1;
    private Integer eventId2;
    private Community community1;
    private Community community2;
    private LocalDate eventDate1;
    private LocalDate eventDate2;
    private LocalTime eventTime1;
    private LocalTime eventTime2;
    private String description1;
    private String description2;

    @BeforeEach
    void setUp() {
        event = new Event();
        eventId1 = 1;
        eventId2 = 2;
        event.setEventId(eventId1);
        community1 = new Community();
        community2 = new Community();
        event.setCommunity(community1);
        eventDate1 = LocalDate.now();
        eventDate2 = LocalDate.now().minusDays(1);
        event.setEventDate(eventDate1);
        eventTime1 = LocalTime.now();
        eventTime2 = LocalTime.now().minusHours(1);
        event.setEventTime(eventTime1);
        description1 = "description1";
        description2 = "description2";
        event.setDescription(description1);
    }

    @AfterEach
    void tearDown() {
        event = null;
        eventId1 = null;
        eventId2 = null;
        community1 = null;
        community2 = null;
        eventDate1 = null;
        eventDate2 = null;
        eventTime1 = null;
        eventTime2 = null;
        description1 = null;
        description2 = null;
    }

    @Test
    void getCommunity() {
        assertEquals(event.getCommunity(), community1);
    }

    @Test
    void setCommunity() {
        event.setCommunity(community2);
        assertEquals(event.getCommunity(), community2);
        assertNotEquals(event.getCommunity(), community1);
    }

    @Test
    void getEventId() {
        assertEquals(event.getEventId(), eventId1);
    }

    @Test
    void setEventId() {
        event.setEventId(eventId2);
        assertEquals(event.getEventId(), eventId2);
        assertNotEquals(event.getEventId(), eventId1);
    }

    @Test
    void getEventDate() {
        assertEquals(event.getEventDate(), eventDate1);
    }

    @Test
    void setEventDate() {
        event.setEventDate(eventDate2);
        assertEquals(event.getEventDate(), eventDate2);
        assertNotEquals(event.getEventDate(), eventDate1);
    }

    @Test
    void getEventTime() {
        assertEquals(event.getEventTime(), eventTime1);
    }

    @Test
    void setEventTime() {
        event.setEventTime(eventTime2);
        assertEquals(event.getEventTime(), eventTime2);
        assertNotEquals(event.getEventTime(), eventTime1);
    }

    @Test
    void getDescription() {
        assertEquals(event.getDescription(), description1);
    }

    @Test
    void setDescription() {
        event.setDescription(description2);
        assertEquals(event.getDescription(), description2);
        assertNotEquals(event.getDescription(), description1);
    }
}