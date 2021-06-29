package com.syntra.tristanbrewee.miniCrm.model.dtos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompleteAddressTest {

    private CompleteAddress completeAddress;
    private Integer addressId1;
    private Integer addressId2;
    private String bus1;
    private String bus2;
    private String city1;
    private String city2;
    private String country1;
    private String country2;
    private String houseNumber1;
    private String houseNumber2;
    private String street1;
    private String street2;
    private String zip1;
    private String zip2;
    private String email1;
    private String email2;
    private String mobile1;
    private String mobile2;
    private String phone1;
    private String phone2;
    private String type1;
    private String type2;

    @BeforeEach
    void setUp() {
        completeAddress = new CompleteAddress();
        addressId1 = 1;
        addressId2 = 2;
        completeAddress.setAddressId(addressId1);
        bus1 = "A";
        bus2 = "B";
        completeAddress.setBus(bus1);
        city1 = "CityOne";
        city2 = "CityTwo";
        completeAddress.setCity(city1);
        country1 = "CountryOne";
        country2 = "CountryTwo";
        completeAddress.setCountry(country1);
        houseNumber1 = "1";
        houseNumber2 = "2";
        completeAddress.setHouseNumber(houseNumber1);
        street1 = "StreetOne";
        street2 = street2 = "StreetTwo";
        completeAddress.setStreet(street1);
        zip1 = "1111";
        zip2 = "2222";
        completeAddress.setZip(zip1);
        email1 = "e@mail.one";
        email2 = "e@mail.two";
        completeAddress.setEmail(email1);
        mobile1 = "123";
        mobile2 = "321";
        completeAddress.setMobile(mobile1);
        phone1 = "987";
        phone2 = "789";
        completeAddress.setPhone(phone1);
        type1 = "private";
        type2 = "work";
        completeAddress.setType(type1);
    }

    @AfterEach
    void tearDown() {
        completeAddress = null;
        addressId1 = null;
        addressId2 = null;
        bus1 = null;
        bus2 = null;
        city1 = null;
        city2 = null;
        country1 = null;
        country2 = null;
        houseNumber1 = null;
        houseNumber2 = null;
        street1 = null;
        street2 = null;
        zip1 = null;
        zip2 = null;
        email1 = null;
        email2 = null;
        mobile1 = null;
        mobile2 = null;
        phone1 = null;
        phone2 = null;
        type1 = null;
        type2 = null;
    }

    @Test
    void getAddressId() {
        assertEquals(completeAddress.getAddressId(), addressId1);
    }

    @Test
    void setAddressId() {
        completeAddress.setAddressId(addressId2);
        assertEquals(completeAddress.getAddressId(), addressId2);
        assertNotEquals(completeAddress.getAddressId(), addressId1);
    }

    @Test
    void getBus() {
        assertEquals(completeAddress.getBus(), bus1);
    }

    @Test
    void setBus() {
        completeAddress.setBus(bus2);
        assertEquals(completeAddress.getBus(), bus2);
        assertNotEquals(completeAddress.getBus(), bus1);
    }

    @Test
    void getCity() {
        assertEquals(completeAddress.getCity(), city1);
    }

    @Test
    void setCity() {
        completeAddress.setCity(city2);
        assertEquals(completeAddress.getCity(), city2);
        assertNotEquals(completeAddress.getCity(), city1);
    }

    @Test
    void getCountry() {
        assertEquals(completeAddress.getCountry(), country1);
    }

    @Test
    void setCountry() {
        completeAddress.setCountry(country2);
        assertEquals(completeAddress.getCountry(), country2);
        assertNotEquals(completeAddress.getCountry(), country1);
    }

    @Test
    void getHouseNumber() {
        assertEquals(completeAddress.getHouseNumber(), houseNumber1);
    }

    @Test
    void setHouseNumber() {
        completeAddress.setHouseNumber(houseNumber2);
        assertEquals(completeAddress.getHouseNumber(), houseNumber2);
        assertNotEquals(completeAddress.getHouseNumber(), houseNumber1);
    }

    @Test
    void getStreet() {
        assertEquals(completeAddress.getStreet(), street1);
    }

    @Test
    void setStreet() {
        completeAddress.setStreet(street2);
        assertEquals(completeAddress.getStreet(), street2);
        assertNotEquals(completeAddress.getStreet(), street1);
    }

    @Test
    void getZip() {
        assertEquals(completeAddress.getZip(), zip1);
    }

    @Test
    void setZip() {
        completeAddress.setZip(zip2);
        assertEquals(completeAddress.getZip(), zip2);
        assertNotEquals(completeAddress.getZip(), zip1);
    }

    @Test
    void getEmail() {
        assertEquals(completeAddress.getEmail(), email1);
    }

    @Test
    void setEmail() {
        completeAddress.setEmail(email2);
        assertEquals(completeAddress.getEmail(), email2);
        assertNotEquals(completeAddress.getEmail(), email1);
    }

    @Test
    void getMobile() {
        assertEquals(completeAddress.getMobile(), mobile1);
    }

    @Test
    void setMobile() {
        completeAddress.setMobile(mobile2);
        assertEquals(completeAddress.getMobile(), mobile2);
        assertNotEquals(completeAddress.getMobile(), mobile1);
    }

    @Test
    void getPhone() {
        assertEquals(completeAddress.getPhone(), phone1);
    }

    @Test
    void setPhone() {
        completeAddress.setPhone(phone2);
        assertEquals(completeAddress.getPhone(), phone2);
        assertNotEquals(completeAddress.getPhone(), phone1);
    }

    @Test
    void getType() {
        assertEquals(completeAddress.getType(), type1);
    }

    @Test
    void setType() {
        completeAddress.setType(type2);
        assertEquals(completeAddress.getType(), type2);
        assertNotEquals(completeAddress.getType(), type1);
    }
}