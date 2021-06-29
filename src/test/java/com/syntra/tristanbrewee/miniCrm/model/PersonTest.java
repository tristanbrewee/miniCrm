package com.syntra.tristanbrewee.miniCrm.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person1;
    private Person person2;
    private Person personNull;
    private Integer personId1;
    private Integer personId2;
    private List<Address> addresses1;
    private List<Address> addresses2;
    private Address address;
    private List<Community> communities1;
    private List<Community> communities2;
    private Community community;
    private String lastName1;
    private String lastName2;
    private String firstName1;
    private String firstName2;
    private LocalDate birthDate1;
    private LocalDate birthDate2;
    private Boolean isActive1;
    private Boolean isActive2;

    @BeforeEach
    void setUp() {
        person1 = new Person();
        person2 = new Person();
        personNull = null;
        personId1 = 1;
        personId2 = 2;
        person1.setPersonId(personId1);
        person2.setPersonId(personId1);
        addresses1 = new ArrayList<>();
        addresses2 = new ArrayList<>();
        address = new Address();
        addresses2.add(address);
        person1.setPerson_address(addresses1);
        person2.setPerson_address(addresses1);
        communities1 = new ArrayList<>();
        communities2 = new ArrayList<>();
        community = new Community();
        communities1.add(community);
        person1.setMember(communities1);
        person2.setMember(communities1);
        lastName1 = "LastNameOne";
        lastName2 = "LastNameTwo";
        person1.setLastName(lastName1);
        person2.setLastName(lastName1);
        firstName1 = "FirstNameOne";
        firstName2 = "FirstNameTwo";
        person1.setFirstName(firstName1);
        person2.setFirstName(firstName1);
        birthDate1 = LocalDate.now();
        birthDate2 = LocalDate.now().minusDays(1);
        person1.setBirthDate(birthDate1);
        person2.setBirthDate(birthDate1);
        isActive1 = true;
        isActive2 = false;
        person1.setActive(isActive1);
        person2.setActive(isActive1);
    }

    @AfterEach
    void tearDown() {
        person1 = null;
        person2 = null;
        personId1 = null;
        personId2 = null;
        addresses1 = null;
        addresses2 = null;
        address = null;
        communities1 = null;
        communities2 = null;
        community = null;
        lastName1 = null;
        lastName2 = null;
        firstName1 = null;
        firstName2 = null;
        birthDate1 = null;
        birthDate2 = null;
        isActive1 = null;
        isActive2 = null;
    }

    @Test
    void testEqualsSameValues() {
        assertTrue(person1.equals(person2));
    }

    @Test
    void testEqualsDifferentPersonId(){
        person2.setPersonId(personId2);
        assertFalse(person1.equals(person2));
    }

    @Test
    void testEqualsDifferentLastName(){
        person2.setLastName(lastName2);
        assertFalse(person1.equals(person2));
    }

    @Test
    void testEqualsDifferentFirstName(){
        person2.setFirstName(firstName2);
        assertFalse(person1.equals(person2));
    }

    @Test
    void testEqualsDifferentIsActive(){
        person2.setActive(isActive2);
        assertFalse(person1.equals(person2));
    }

    @Test
    void testEqualsDifferentBirthDate(){
        person2.setBirthDate(birthDate2);
        assertFalse(person1.equals(person2));
    }

    @Test
    void testEqualsDifferentClass(){
        assertFalse(person1.equals(community));
    }

    @Test
    void testEqualsOtherObjectIsNull(){
        assertFalse(person1.equals(personNull));
    }

    @Test
    void testHashCode() {
        Object[] objects = {personId1, addresses1, communities1, lastName1, firstName1, birthDate1, isActive1};
        assertEquals(person1.hashCode(), Arrays.hashCode(objects));
    }

    @Test
    void getPersonId() {
        assertEquals(person1.getPersonId(), personId1);
    }

    @Test
    void setPersonId() {
        person1.setPersonId(personId2);
        assertEquals(person1.getPersonId(), personId2);
        assertNotEquals(person1.getPersonId(), personId1);
    }

    @Test
    void getLastName() {
        assertEquals(person1.getLastName(), lastName1);
    }

    @Test
    void setLastName() {
        person1.setLastName(lastName2);
        assertEquals(person1.getLastName(), lastName2);
        assertNotEquals(person1.getLastName(), lastName1);
    }

    @Test
    void getFirstName() {
        assertEquals(person1.getFirstName(), firstName1);
    }

    @Test
    void setFirstName() {
        person1.setFirstName(firstName2);
        assertEquals(person1.getFirstName(), firstName2);
        assertNotEquals(person1.getFirstName(), firstName1);
    }

    @Test
    void getBirthDate() {
        assertEquals(person1.getBirthDate(), birthDate1);
    }

    @Test
    void setBirthDate() {
        person1.setBirthDate(birthDate2);
        assertEquals(person1.getBirthDate(), birthDate2);
        assertNotEquals(person1.getBirthDate(), birthDate1);
    }

    @Test
    void getPerson_address() {
        assertEquals(person1.getPerson_address(), addresses1);
    }

    @Test
    void setPerson_address() {
        person1.setPerson_address(addresses2);
        assertEquals(person1.getPerson_address(), addresses2);
        assertNotEquals(person1.getPerson_address(), addresses1);
    }

    @Test
    void getMember() {
        assertEquals(person1.getMember(), communities1);
    }

    @Test
    void setMember() {
        person1.setMember(communities2);
        assertEquals(person1.getMember(), communities2);
        assertNotEquals(person1.getMember(), communities1);
    }

    @Test
    void getActive() {
        assertEquals(person1.getActive(), isActive1);
    }

    @Test
    void setActive() {
        person1.setActive(isActive2);
        assertEquals(person1.getActive(), isActive2);
        assertNotEquals(person1.getActive(), isActive1);
    }
}