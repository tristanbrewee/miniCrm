package com.syntra.tristanbrewee.miniCrm.model;

import com.syntra.tristanbrewee.miniCrm.model.idclasses.PersonAddressId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonAddressTest {

    private PersonAddress personAddress;
    private PersonAddressId personAddressId1;
    private PersonAddressId personAddressId2;
    private String email1;
    private String email2;
    private String phone1;
    private String phone2;
    private String mobile1;
    private String mobile2;
    private String type1;
    private String type2;

    @BeforeEach
    void setUp() {
        personAddress = new PersonAddress();
        personAddressId1 = new PersonAddressId(1, 1);
        personAddressId2 = new PersonAddressId(2, 2);
        personAddress.setPersonAddressId(personAddressId1);
        email1 = "e@mail.one";
        email2 = "e@mail.two";
        personAddress.setEmail(email1);
        phone1 = "123";
        phone2 = "321";
        personAddress.setPhone(phone1);
        mobile1 = "987";
        mobile2 = "789";
        personAddress.setMobile(mobile1);
        type1 = "private";
        type2 = "work";
        personAddress.setType(type1);
    }

    @AfterEach
    void tearDown() {
        personAddress = null;
        personAddressId1 = null;
        personAddressId2 = null;
        email1 = null;
        email2 = null;
        phone1 = null;
        phone2 = null;
        mobile1 = null;
        mobile2 = null;
        type1 = null;
        type2 = null;
    }

    @Test
    void getPersonAddressId() {
        assertEquals(personAddress.getPersonAddressId(), personAddressId1);
    }

    @Test
    void setPersonAddressId() {
        personAddress.setPersonAddressId(personAddressId2);
        assertEquals(personAddress.getPersonAddressId(), personAddressId2);
        assertNotEquals(personAddress.getPersonAddressId(), personAddressId1);
    }

    @Test
    void getEmail() {
        assertEquals(personAddress.getEmail(), email1);
    }

    @Test
    void setEmail() {
        personAddress.setEmail(email2);
        assertEquals(personAddress.getEmail(), email2);
        assertNotEquals(personAddress.getEmail(), email1);
    }

    @Test
    void getPhone() {
        assertEquals(personAddress.getPhone(), phone1);
    }

    @Test
    void setPhone() {
        personAddress.setPhone(phone2);
        assertEquals(personAddress.getPhone(), phone2);
        assertNotEquals(personAddress.getPhone(), phone1);
    }

    @Test
    void getMobile() {
        assertEquals(personAddress.getMobile(), mobile1);
    }

    @Test
    void setMobile() {
        personAddress.setMobile(mobile2);
        assertEquals(personAddress.getMobile(), mobile2);
        assertNotEquals(personAddress.getMobile(), mobile1);
    }

    @Test
    void getType() {
        assertEquals(personAddress.getType(), type1);
    }

    @Test
    void setType() {
        personAddress.setType(type2);
        assertEquals(personAddress.getType(), type2);
        assertNotEquals(personAddress.getType(), type1);
    }
}