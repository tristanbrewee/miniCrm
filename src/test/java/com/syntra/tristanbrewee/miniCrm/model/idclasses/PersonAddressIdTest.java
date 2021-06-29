package com.syntra.tristanbrewee.miniCrm.model.idclasses;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PersonAddressIdTest {

    private PersonAddressId personAddressId1;
    private PersonAddressId personAddressId2;
    private PersonAddressId personAddressIdNull;
    private Integer personId1;
    private Integer personId2;
    private Integer addressId1;
    private Integer addressId2;

    @BeforeEach
    void setUp() {
        personAddressId1 = new PersonAddressId();
        personAddressId2 = new PersonAddressId();
        personAddressIdNull = null;
        personId1 = 1;
        personId2 = 2;
        personAddressId1.setPerson_id(personId1);
        personAddressId2.setPerson_id(personId1);
        addressId1 = 1;
        addressId2 = 2;
        personAddressId1.setAddress_id(addressId1);
        personAddressId2.setAddress_id(addressId1);
    }

    @AfterEach
    void tearDown() {
        personAddressId1 = null;
        personAddressId2 = null;
        personId1 = null;
        personId2 = null;
        addressId1 = null;
        addressId2 = null;
    }

    @Test
    void getPerson_id() {
        assertEquals(personAddressId1.getPerson_id(), personId1);
    }

    @Test
    void setPerson_id() {
        personAddressId1.setPerson_id(personId2);
        assertEquals(personAddressId1.getPerson_id(), personId2);
        assertNotEquals(personAddressId1.getPerson_id(), personId1);
    }

    @Test
    void getAddress_id() {
        assertEquals(personAddressId1.getAddress_id(), addressId1);
    }

    @Test
    void setAddress_id() {
        personAddressId1.setAddress_id(addressId2);
        assertEquals(personAddressId1.getAddress_id(), addressId2);
        assertNotEquals(personAddressId1.getAddress_id(), addressId1);
    }

    @Test
    void testEqualsSameObject() {
        assertTrue(personAddressId1.equals(personAddressId1));
    }

    @Test
    void testEqualsObjectIsNull(){
        assertFalse(personAddressId1.equals(personAddressIdNull));
    }

    @Test
    void testEqualsObjectIsDifferentClass(){
        assertFalse(personAddressId1.equals(personId1));
    }

    @Test
    void testEqualsDifferentPersonId(){
        personAddressId2.setPerson_id(personId2);
        assertFalse(personAddressId1.equals(personAddressId2));
    }

    @Test
    void testEqualsDifferentAddressId(){
        personAddressId2.setAddress_id(addressId2);
        assertFalse(personAddressId1.equals(personAddressId2));
    }

    @Test
    void testEqualsSameValues(){
        assertTrue(personAddressId1.equals(personAddressId2));
    }

    @Test
    void testHashCode() {
        Object[] objects = {personId1, addressId1};
        assertEquals(personAddressId1.hashCode(), Arrays.hashCode(objects));
    }
}