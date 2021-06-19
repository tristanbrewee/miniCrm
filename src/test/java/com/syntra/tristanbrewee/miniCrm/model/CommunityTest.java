package com.syntra.tristanbrewee.miniCrm.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommunityTest {

    private Community community;
    private Integer id1;
    private Integer id2;
    private String description1;
    private String description2;
    private List<Person> members1;
    private List<Person> members2;
    private Person person;
    private List<Event> events1;
    private List<Event> events2;
    private Event event;

    @BeforeEach
    void setUp() {
        id1 = 1;
        id2 = 2;
        description1 = "description1";
        description2 = "description2";
        members1 = new ArrayList<>();
        members2 = new ArrayList<>();
        person = new Person();
        events1 = new ArrayList<>();
        events2 = new ArrayList<>();
        event = new Event();
        community = new Community();

        members1.add(person);
        events1.add(event);

        community.setCommunityId(id1);
        community.setDescription(description1);
        community.setMember(members1);
        community.setEvent(events1);
    }

    @AfterEach
    void tearDown() {
        id1 = null;
        id2 = null;
        description1 = null;
        description2 = null;
        members1 = null;
        members2 = null;
        events1 = null;
        events2 = null;
        community = null;
    }

    @Test
    void getCommunityId() {
        assertEquals(community.getCommunityId(), id1);
    }

    @Test
    void setCommunityId() {
        community.setCommunityId(id2);
        assertEquals(community.getCommunityId(), id2);
        assertNotEquals(community.getCommunityId(), id1);
    }

    @Test
    void getDescription() {
        assertEquals(community.getDescription(), description1);
    }

    @Test
    void setDescription() {
        community.setDescription(description2);
        assertEquals(community.getDescription(), description2);
        assertNotEquals(community.getDescription(), description1);
    }

    @Test
    void getMember() {
        assertEquals(community.getMember(), members1);
    }

    @Test
    void setMember() {
        community.setMember(members2);
        assertEquals(community.getMember(), members2);
        assertNotEquals(community.getMember(), members1);
    }

    @Test
    void getEvent() {
        assertEquals(community.getEvent(), events1);
    }

    @Test
    void setEvent() {
        community.setEvent(events2);
        assertEquals(community.getEvent(), events2);
        assertNotEquals(community.getEvent(), events1);
    }
}