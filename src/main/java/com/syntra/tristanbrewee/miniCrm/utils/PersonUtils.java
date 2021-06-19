package com.syntra.tristanbrewee.miniCrm.utils;

import com.syntra.tristanbrewee.miniCrm.model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonUtils {

    public static List<String> checkForNullValues(Person person){
        List<String> errors = new ArrayList<>();
        if (checkIfFirstNameIsNull(person))
            errors.add("The first name can't be null");
        if (checkIfLastNameIsNull(person))
            errors.add("The last name can't be null");
        return errors;
    }

    public static List<String> checkForInvalidInput(Person person){
        List<String> errors = new ArrayList<>();
        if (!checkIdBirthDateIsBeforeTomorrow(person))
            errors.add("The birth date must be no later then today");
        if (!checkFirstNameIsValid(person))
            errors.add("The first name can only contain letters, single dashes, single apostrophes, and single spaces");
        if (!checkLastNameIsValid(person))
            errors.add("The last name can only contain letters, single dashes, single apostrophes, and single spaces");
        return errors;
    }

    private static boolean checkIfFirstNameIsNull(Person person){
        return GeneralUtils.checkIfObjectIsNull(person.getFirstName());
    }

    private static boolean checkIfLastNameIsNull(Person person){
        return GeneralUtils.checkIfObjectIsNull(person.getLastName());
    }

    private static boolean checkIdBirthDateIsBeforeTomorrow(Person person){
        return GeneralUtils.checkIfDateBeforeDate(person.getBirthDate(), LocalDate.now().plusDays(1));
    }

    private static boolean checkFirstNameIsValid(Person person){
        return GeneralUtils.checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces(person.getFirstName());
    }

    private static boolean checkLastNameIsValid(Person person){
        return GeneralUtils.checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces(person.getLastName());
    }
}
