package com.syntra.tristanbrewee.miniCrm.model.dtos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompletePersonTest {

    private CompletePerson completePerson;
    private Integer personId1;
    private Integer personId2;
    private LocalDate birthDate1;
    private LocalDate birthDate2;
    private String firstName1;
    private String firstName2;
    private String lastName1;
    private String lastName2;
    private Boolean isActive1;
    private Boolean isActive2;
    private List<CompleteAddress> completeAddresses1;
    private List<CompleteAddress> completeAddresses2;
    private CompleteAddress completeAddress;
    private List<CompleteCommunity> completeCommunities1;
    private List<CompleteCommunity> completeCommunities2;
    private CompleteCommunity completeCommunity;

    @BeforeEach
    void setUp() {
        completePerson = new CompletePerson();
        personId1 = 1;
        personId2 = 2;
        completePerson.setPersonId(personId1);
        birthDate1 = LocalDate.now();
        birthDate2 = LocalDate.now().minusDays(1);
        completePerson.setBirthDate(birthDate1);
        firstName1 = "FirstNameOne";
        firstName2 = "FirstNameTwo";
        completePerson.setFirstName(firstName1);
        lastName1 = "LastNameOne";
        lastName2 = "LastNameTwo";
        completePerson.setLastName(lastName1);
        isActive1 = true;
        isActive2 = false;
        completePerson.setIsActive(isActive1);
        completeAddresses1 = new ArrayList<>();
        completeAddresses2 = new ArrayList<>();
        completeAddress = new CompleteAddress();
        completeAddresses1.add(completeAddress);
        completePerson.setCompleteAddress(completeAddresses1);
        completeCommunities1 = new ArrayList<>();
        completeCommunities2 = new ArrayList<>();
        completeCommunity = new CompleteCommunity();
        completeCommunities1.add(completeCommunity);
        completePerson.setCompleteCommunities(completeCommunities1);
    }

    @AfterEach
    void tearDown() {
        completePerson = null;
        personId1 = null;
        personId2 = null;
        birthDate1 = null;
        birthDate2 = null;
        firstName1 = null;
        firstName2 = null;
        lastName1 = null;
        lastName2 = null;
        isActive1 = null;
        isActive2 = null;
        completeAddresses1 = null;
        completeAddresses2 = null;
        completeAddress = null;
        completeCommunities1 = null;
        completeCommunities2 = null;
        completeCommunity = null;
    }

    @Test
    void getPersonId() {
        assertEquals(completePerson.getPersonId(), personId1);
    }

    @Test
    void setPersonId() {
        completePerson.setPersonId(personId2);
        assertEquals(completePerson.getPersonId(), personId2);
        assertNotEquals(completePerson.getPersonId(), personId1);
    }

    @Test
    void getBirthDate() {
        assertEquals(completePerson.getBirthDate(), birthDate1);
    }

    @Test
    void setBirthDate() {
        completePerson.setBirthDate(birthDate2);
        assertEquals(completePerson.getBirthDate(), birthDate2);
        assertNotEquals(completePerson.getBirthDate(), birthDate1);
    }

    @Test
    void getFirstName() {
        assertEquals(completePerson.getFirstName(), firstName1);
    }

    @Test
    void setFirstName() {
        completePerson.setFirstName(firstName2);
        assertEquals(completePerson.getFirstName(), firstName2);
        assertNotEquals(completePerson.getFirstName(), firstName1);
    }

    @Test
    void getLastName() {
        assertEquals(completePerson.getLastName(), lastName1);
    }

    @Test
    void setLastName() {
        completePerson.setLastName(lastName2);
        assertEquals(completePerson.getLastName(), lastName2);
        assertNotEquals(completePerson.getLastName(), lastName1);
    }

    @Test
    void getIsActive() {
        assertEquals(completePerson.getIsActive(), isActive1);
    }

    @Test
    void setIsActive() {
        completePerson.setIsActive(isActive2);
        assertEquals(completePerson.getIsActive(), isActive2);
        assertNotEquals(completePerson.getIsActive(), isActive1);
    }

    @Test
    void getCompleteAddress() {
        assertEquals(completePerson.getCompleteAddress(), completeAddresses1);
    }

    @Test
    void setCompleteAddress() {
        completePerson.setCompleteAddress(completeAddresses2);
        assertEquals(completePerson.getCompleteAddress(), completeAddresses2);
        assertNotEquals(completePerson.getCompleteAddress(), completeAddresses1);
    }

    @Test
    void getCompleteCommunities() {
        assertEquals(completePerson.getCompleteCommunities(), completeCommunities1);
    }

    @Test
    void setCompleteCommunities() {
        completePerson.setCompleteCommunities(completeCommunities2);
        assertEquals(completePerson.getCompleteCommunities(), completeCommunities2);
        assertNotEquals(completePerson.getCompleteCommunities(), completeCommunities1);
    }
}