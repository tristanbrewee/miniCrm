package com.syntra.tristanbrewee.miniCrm.model.dtos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CompleteMemberTest {

    private CompleteMember completeMember;
    private Integer personId1;
    private Integer personId2;
    private String lastName1;
    private String lastName2;
    private String firstName1;
    private String firstName2;
    private LocalDate since1;
    private LocalDate since2;
    private LocalDate until1;
    private LocalDate until2;

    @BeforeEach
    void setUp() {
        completeMember = new CompleteMember();
        personId1 = 1;
        personId2 = 2;
        completeMember.setPersonId(personId1);
        lastName1 = "LastNameOne";
        lastName2 = "LastNameTwo";
        completeMember.setLastName(lastName1);
        firstName1 = "FirstNameOne";
        firstName2 = "FirstNameTwo";
        completeMember.setFirstName(firstName1);
        since1 = LocalDate.now();
        since2 = LocalDate.now().minusDays(1);
        completeMember.setSince(since1);
        until1 = LocalDate.now();
        until2 = LocalDate.now().minusDays(1);
        completeMember.setUntil(until1);
    }

    @AfterEach
    void tearDown() {
        completeMember = null;
        personId1 = null;
        personId2 = null;
        lastName1 = null;
        lastName2 = null;
        firstName1 = null;
        firstName2 = null;
        since1 = null;
        since2 = null;
        until1 = null;
        until2 = null;
    }

    @Test
    void getPersonId() {
        assertEquals(completeMember.getPersonId(), personId1);
    }

    @Test
    void setPersonId() {
        completeMember.setPersonId(personId2);
        assertEquals(completeMember.getPersonId(), personId2);
        assertNotEquals(completeMember.getPersonId(), personId1);
    }

    @Test
    void getLastName() {
        assertEquals(completeMember.getLastName(), lastName1);
    }

    @Test
    void setLastName() {
        completeMember.setLastName(lastName2);
        assertEquals(completeMember.getLastName(), lastName2);
        assertNotEquals(completeMember.getLastName(), lastName1);
    }

    @Test
    void getFirstName() {
        assertEquals(completeMember.getFirstName(), firstName1);
    }

    @Test
    void setFirstName() {
        completeMember.setFirstName(firstName2);
        assertEquals(completeMember.getFirstName(), firstName2);
        assertNotEquals(completeMember.getFirstName(), firstName1);
    }

    @Test
    void getSince() {
        assertEquals(completeMember.getSince(), since1);
    }

    @Test
    void setSince() {
        completeMember.setSince(since2);
        assertEquals(completeMember.getSince(), since2);
        assertNotEquals(completeMember.getSince(), since1);
    }

    @Test
    void getUntil() {
        assertEquals(completeMember.getUntil(), until1);
    }

    @Test
    void setUntil() {
        completeMember.setUntil(until2);
        assertEquals(completeMember.getUntil(), until2);
        assertNotEquals(completeMember.getUntil(), until1);
    }
}