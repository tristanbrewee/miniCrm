package com.syntra.tristanbrewee.miniCrm.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Address address;
    private Integer addressId1;
    private Integer addressId2;
    private List<Person> persons1;
    private List<Person> persons2;
    private Person person;
    private String street1;
    private String street2;
    private String houseNumber1;
    private String houseNumber2;
    private String bus1;
    private String bus2;
    private String city1;
    private String city2;
    private String zip1;
    private String zip2;
    private String country1;
    private String country2;

    @BeforeEach
    void setUp() {
        address = new Address();
        addressId1 = 1;
        addressId2 = 2;
        address.setAddressId(addressId1);
        persons1 = new ArrayList<>();
        persons2 = new ArrayList<>();
        person = new Person();
        persons2.add(person);
        address.setPerson_address(persons1);
        street1 = "StreetOne";
        street2 = "StreetTwo";
        address.setStreet(street1);
        houseNumber1 = "1";
        houseNumber2  ="2";
        address.setHouseNumber(houseNumber1);
        bus1 = "A";
        bus2 = "B";
        address.setBus(bus1);
        city1 = "CityOne";
        city2 = "CityTwo";
        address.setCity(city1);
        zip1 = "1111";
        zip2 = "2222";
        address.setZip(zip1);
        country1 = "CountryOne";
        country2 = "CountryTwo";
        address.setCountry(country1);
    }

    @AfterEach
    void tearDown() {
        address = null;
        addressId1 = null;
        addressId2 = null;
        persons1 = null;
        persons2 = null;
        person = null;
        street1 = null;
        street2 = null;
        houseNumber1 = null;
        houseNumber2 = null;
        bus1 = null;
        bus2 = null;
        city1 = null;
        city2 = null;
        zip1 = null;
        zip2 = null;
        country1 = null;
        country2 = null;
    }

    @Test
    void getAddressId() {
        assertEquals(address.getAddressId(), addressId1);
    }

    @Test
    void setAddressId() {
        address.setAddressId(addressId2);
        assertEquals(address.getAddressId(), addressId2);
        assertNotEquals(address.getAddressId(), addressId1);
    }

    @Test
    void getStreet() {
        assertEquals(address.getStreet(), street1);
    }

    @Test
    void setStreet() {
        address.setStreet(street2);
        assertEquals(address.getStreet(), street2);
        assertNotEquals(address.getStreet(), street1);
    }

    @Test
    void getHouseNumber() {
        assertEquals(address.getHouseNumber(), houseNumber1);
    }

    @Test
    void setHouseNumber() {
        address.setHouseNumber(houseNumber2);
        assertEquals(address.getHouseNumber(), houseNumber2);
        assertNotEquals(address.getHouseNumber(), houseNumber1);
    }

    @Test
    void getBus() {
        assertEquals(address.getBus(), bus1);
    }

    @Test
    void setBus() {
        address.setBus(bus2);
        assertEquals(address.getBus(), bus2);
        assertNotEquals(address.getBus(), bus1);
    }

    @Test
    void getCity() {
        assertEquals(address.getCity(), city1);
    }

    @Test
    void setCity() {
        address.setCity(city2);
        assertEquals(address.getCity(), city2);
        assertNotEquals(address.getCity(), city1);
    }

    @Test
    void getZip() {
        assertEquals(address.getZip(), zip1);
    }

    @Test
    void setZip() {
        address.setZip(zip2);
        assertEquals(address.getZip(), zip2);
        assertNotEquals(address.getZip(), zip1);
    }

    @Test
    void getCountry() {
        assertEquals(address.getCountry(), country1);
    }

    @Test
    void setCountry() {
        address.setCountry(country2);
        assertEquals(address.getCountry(), country2);
        assertNotEquals(address.getCity(), country1);
    }

    @Test
    void getPerson_address(){
        assertEquals(address.getPerson_address(), persons1);
    }

    @Test
    void setPerson_address(){
        address.setPerson_address(persons2);
        assertEquals(address.getPerson_address(), persons2);
        assertNotEquals(address.getPerson_address(), persons1);
    }
}