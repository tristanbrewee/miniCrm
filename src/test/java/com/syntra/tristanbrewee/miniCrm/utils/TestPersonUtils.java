package com.syntra.tristanbrewee.miniCrm.utils;

import static org.junit.Assert.*;
import static com.syntra.tristanbrewee.miniCrm.utils.PersonUtils.*;

import com.syntra.tristanbrewee.miniCrm.model.Address;
import com.syntra.tristanbrewee.miniCrm.model.Community;
import com.syntra.tristanbrewee.miniCrm.model.Person;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestPersonUtils {

    private String nullString;
    private String firstNameInvalid;
    private String firstNameValid;
    private String lastNameInvalid;
    private String lastNameValid;
    private LocalDate yesterday;
    private LocalDate tomorrow;
    final private List<Address> addresses = new ArrayList<>();
    final private List<Community> communities = new ArrayList<>();
    private Person person;

    private String errorFirstNameNull;
    private String errorLastNameNull;
    private String errorBirthDateInvalid;
    private String errorFirstNameInvalid;
    private String errorLastNameInvalid;

    @Before
    public void startUp(){
        nullString = null;
        firstNameInvalid = "0123";
        firstNameValid = "Bob";
        lastNameInvalid = "0123";
        lastNameValid = "Bobby";
        yesterday = LocalDate.now().minusDays(1);
        tomorrow = LocalDate.now().plusDays(1);
        person = new Person();
        person.setPersonId(1);
        person.setFirstName(firstNameValid);
        person.setLastName(lastNameValid);
        person.setBirthDate(yesterday);
        person.setPerson_address(addresses);
        person.setMember(communities);

        errorFirstNameNull = "The first name can't be null";
        errorLastNameNull = "The last name can't be null";
        errorBirthDateInvalid = "The birth date must be no later then today";
        errorFirstNameInvalid = "The first name can't contain any numbers";
        errorLastNameInvalid = "The last name can't contain any numbers";
    }

    @Test
    public void testCheckForNullValues(){
        List<String> errors = checkForNullValues(person);
        assertEquals(0, errors.size());

        person.setFirstName(nullString);
        errors = checkForNullValues(person);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorFirstNameNull));

        person.setFirstName(firstNameValid);
        person.setLastName(nullString);
        errors = checkForNullValues(person);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorLastNameNull));
    }

    @Test
    public void testCheckForInvalidInput(){
        List<String> errors = checkForInvalidInput(person);
        assertEquals(0, errors.size());

        person.setFirstName(firstNameInvalid);
        errors = checkForInvalidInput(person);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorFirstNameInvalid));

        person.setFirstName(firstNameValid);
        person.setLastName(lastNameInvalid);
        errors = checkForInvalidInput(person);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorLastNameInvalid));

        person.setLastName(lastNameValid);
        person.setBirthDate(tomorrow);
        errors = checkForInvalidInput(person);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorBirthDateInvalid));
    }
}
