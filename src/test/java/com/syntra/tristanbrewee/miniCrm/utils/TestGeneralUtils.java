package com.syntra.tristanbrewee.miniCrm.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.syntra.tristanbrewee.miniCrm.utils.GeneralUtils.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestGeneralUtils {

    private String nullString;
    private String letterString;
    private String numberString;
    private String letterAndNumberString;
    private String specialCharacterString;
    private String validEmailString;
    private String invalidEmail;
    private LocalDate yesterday;
    private LocalDate today;
    private LocalTime now;
    private LocalTime inOneHour;

    @Before
    public void setUp(){
        nullString = null;
        letterString = "abc";
        numberString = "123";
        letterAndNumberString = "abc123";
        specialCharacterString = ";:=,";
        validEmailString = "test@test.test";
        invalidEmail = "test.test@test";
        yesterday = LocalDate.now().minusDays(1);
        today = LocalDate.now();
        now = LocalTime.now();
        inOneHour = LocalTime.now().plusHours(1);
    }

    @Test
    public void testCheckIfObjectIsNull(){
        assertTrue(checkIfObjectIsNull(nullString));
        assertFalse(checkIfObjectIsNull(letterString));
    }

    @Test
    public void testCheckIfDateBeforeDate(){
        assertTrue(checkIfDateBeforeDate(yesterday, today));
        assertFalse(checkIfDateBeforeDate(today, yesterday));
    }

    @Test
    public void testCheckIfDateAfterDate(){
        assertTrue(checkIfDateAfterDate(today, yesterday));
        assertFalse(checkIfDateAfterDate(yesterday, today));
    }

//    @Test
//    public void testCheckIfStringContainsNoNumbers(){
//        assertTrue(checkIfStringContainsNoNumbers(letterString));
//        assertFalse(checkIfStringContainsNoNumbers(numberString));
//    }
//
//    @Test
//    public void testCheckIfStringIsNumeral(){
//        assertTrue(checkIfStringIsNumeral(numberString));
//        assertFalse(checkIfStringIsNumeral(letterString));
//    }
//
//    @Test
//    public void testCheckIfStringContainsOnlyLettersAndNumbers(){
//        assertTrue(checkIfStringContainsOnlyLettersAndNumbers(letterAndNumberString));
//        assertFalse(checkIfStringContainsOnlyLettersAndNumbers(specialCharacterString));
//    }
//
//    @Test
//    public void testCheckIfStringContainsAmpersant(){
//        assertTrue(checkIfStringContainsAmpersant(validEmailString));
//        assertFalse(checkIfStringContainsAmpersant(numberString));
//    }
//
//    @Test
//    public void testCheckIfStringContainsDot(){
//        assertTrue(checkIfStringContainsDot(validEmailString));
//        assertFalse(checkIfStringContainsDot(numberString));
//    }
//
//    @Test
//    public void testCheckIfStringContainsAmpersantAndOnlyOneDotAfterAmpersant(){
//        assertTrue(checkIfStringContainsAmpersantAndOnlyOneDotAfterAmpersant(validEmailString));
//        assertFalse(checkIfStringContainsAmpersantAndOnlyOneDotAfterAmpersant(invalidEmail));
//    }
//
//    @Test
//    public void testCheckIfDateIsToday(){
//        assertTrue(checkIfDateIsToday(today));
//        assertFalse(checkIfDateIsToday(yesterday));
//    }
//
//    @Test
//    public void testCheckIfTmeIsAfterThisMoment(){
//        assertTrue(checkIfTmeIsAfterThisMoment(inOneHour));
//        assertFalse(checkIfTmeIsAfterThisMoment(now));
//    }

}
