package com.syntra.tristanbrewee.miniCrm.utils;

import static org.junit.Assert.*;
import static com.syntra.tristanbrewee.miniCrm.utils.AddressUtils.*;

import com.syntra.tristanbrewee.miniCrm.model.Address;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestAddressUtils {

    private String nullString;
    private String streetInvalid;
    private String streetValid;
    private String houseNumberInvalid;
    private String houseNumberValid;
    private String busInvalid;
    private String busValid;
    private String zipInvalid;
    private String zipValid;
    private String countryInvalid;
    private String countryValid;
    private String cityInvalid;
    private String cityValid;
    private Address address;
    private Address addressNull;

    private String errorStreetNull;
    private String errorHouseNumberNull;
    private String errorCityNull;
    private String errorZipNull;
    private String errorCountryNull;

    private String errorStreetInvalid;
    private String errorHouseNumberInvalid;
    private String errorBusInvalid;
    private String errorCityInvalid;
    private String errorZipInvalid;
    private String errorCountryInvalid;

    @Before
    public void setUp(){
        nullString = null;
        streetInvalid = "123";
        streetValid = "street";
        houseNumberInvalid = "abc";
        houseNumberValid = "12";
        busInvalid = ":;nvqkjv:n";
        busValid = "1A";
        zipInvalid = "qvfd";
        zipValid = "9000";
        countryInvalid = "123";
        countryValid = "België";
        cityInvalid = "123";
        cityValid = "Gent";

        address = new Address();
        address.setAddressId(1);
        address.setStreet(streetValid);
        address.setHouseNumber(houseNumberValid);
        address.setBus(busValid);
        address.setZip(zipValid);
        address.setCountry(countryValid);
        address.setCity(cityValid);

        addressNull = new Address();
        addressNull.setAddressId(1);
        addressNull.setStreet(nullString);
        addressNull.setHouseNumber(nullString);
        addressNull.setBus(nullString);
        addressNull.setZip(nullString);
        addressNull.setCountry(nullString);
        addressNull.setCity(nullString);

        errorStreetNull = "Street can't be null if any value isn't null";
        errorHouseNumberNull = "House number can't be null if any value isn't null";
        errorCityNull = "City can't be null if any value isn't null";
        errorZipNull = "Zip can't be null if any value isn't null";
        errorCountryNull = "Country can't be null if any value isn't null";

        errorStreetInvalid = "Street can not contain any numbers";
        errorHouseNumberInvalid = "House number can only contain numbers";
        errorBusInvalid = "Bus can only contain numbers and letters";
        errorCityInvalid = "City can not contain any numbers";
        errorZipInvalid = "Zip can only contain numbers";
        errorCountryInvalid = "Country can not contain any numbers";
    }

    @Test
    public void testCheckIfAllFieldsAreNull(){
        assertTrue(checkIfAllFieldsAreNull(addressNull));

        addressNull.setStreet(streetValid);
        assertFalse(checkIfAllFieldsAreNull(addressNull));

        addressNull.setStreet(nullString);
        addressNull.setHouseNumber(houseNumberValid);
        assertFalse(checkIfAllFieldsAreNull(addressNull));

        addressNull.setHouseNumber(nullString);
        addressNull.setBus(busValid);
        assertFalse(checkIfAllFieldsAreNull(addressNull));

        addressNull.setBus(nullString);
        addressNull.setCity(cityValid);
        assertFalse(checkIfAllFieldsAreNull(addressNull));

        addressNull.setCity(nullString);
        addressNull.setZip(zipValid);
        assertFalse(checkIfAllFieldsAreNull(addressNull));

        addressNull.setZip(nullString);
        addressNull.setCity(countryValid);
        assertFalse(checkIfAllFieldsAreNull(addressNull));
    }

    @Test
    public void testCheckIfNoFieldIsNullExceptBusIfAnyFieldIsNotNull(){
        List<String> errors = checkIfNoFieldIsNullExceptBusIfAnyFieldIsNotNull(addressNull);
        assertEquals(0, errors.size());

        errors = checkIfNoFieldIsNullExceptBusIfAnyFieldIsNotNull(address);
        assertEquals(0, errors.size());

        address.setBus(nullString);
        errors = checkIfNoFieldIsNullExceptBusIfAnyFieldIsNotNull(address);
        assertEquals(0, errors.size());

        address.setStreet(nullString);
        errors = checkIfNoFieldIsNullExceptBusIfAnyFieldIsNotNull(address);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorStreetNull));

        address.setStreet(streetValid);
        address.setHouseNumber(nullString);
        errors = checkIfNoFieldIsNullExceptBusIfAnyFieldIsNotNull(address);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorHouseNumberNull));

        address.setHouseNumber(houseNumberValid);
        address.setCity(nullString);
        errors = checkIfNoFieldIsNullExceptBusIfAnyFieldIsNotNull(address);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorCityNull));

        address.setCity(cityValid);
        address.setZip(nullString);
        errors = checkIfNoFieldIsNullExceptBusIfAnyFieldIsNotNull(address);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorZipNull));

        address.setZip(zipValid);
        address.setCountry(nullString);
        errors = checkIfNoFieldIsNullExceptBusIfAnyFieldIsNotNull(address);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorCountryNull));
    }

    @Test
    public void testCheckIfAllFieldsHaveCorrectInputIfAnyFieldIsNotNull(){
        List<String> errors = checkIfAllFieldsHaveCorrectInputIfAnyFieldIsNotNull(addressNull);
        assertEquals(0, errors.size());

        errors = checkIfAllFieldsHaveCorrectInputIfAnyFieldIsNotNull(address);
        assertEquals(0, errors.size());

        address.setBus(nullString);
        errors = checkIfAllFieldsHaveCorrectInputIfAnyFieldIsNotNull(address);
        assertEquals(0, errors.size());

        address.setStreet(streetInvalid);
        errors = checkIfAllFieldsHaveCorrectInputIfAnyFieldIsNotNull(address);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorStreetInvalid));

        address.setStreet(streetValid);
        address.setHouseNumber(houseNumberInvalid);
        errors = checkIfAllFieldsHaveCorrectInputIfAnyFieldIsNotNull(address);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorHouseNumberInvalid));

        address.setHouseNumber(houseNumberValid);
        address.setBus(busInvalid);
        errors = checkIfAllFieldsHaveCorrectInputIfAnyFieldIsNotNull(address);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorBusInvalid));

        address.setBus(busValid);
        address.setCity(cityInvalid);
        errors = checkIfAllFieldsHaveCorrectInputIfAnyFieldIsNotNull(address);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorCityInvalid));

        address.setCity(cityValid);
        address.setZip(zipInvalid);
        errors = checkIfAllFieldsHaveCorrectInputIfAnyFieldIsNotNull(address);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorZipInvalid));

        address.setZip(zipValid);
        address.setCountry(countryInvalid);
        errors = checkIfAllFieldsHaveCorrectInputIfAnyFieldIsNotNull(address);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorCountryInvalid));
    }
}
