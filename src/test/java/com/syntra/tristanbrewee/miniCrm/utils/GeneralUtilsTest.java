package com.syntra.tristanbrewee.miniCrm.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static com.syntra.tristanbrewee.miniCrm.utils.GeneralUtils.*;

class GeneralUtilsTest {

    private LocalDate yesterday;
    private LocalDate today;
    private LocalDate tomorrow;
    private LocalTime anHourAgo;
    private LocalTime thisMoment;
    private LocalTime inAnHour;
    private Character charNumber;
    private Character charDash;
    private Character charApostrophe;
    private Character charSpace;
    private Character charSlash;
    private Character charLetter;
    private String stringNull;
    private String stringNotAllowedCharacters;
    private String stringWithAmpersant;
    private String stringWithoutAmpersant;
    private String stringWithDot;
    private String stringWithoutDot;
    private String stringWithAmpersantAndOneDotAfterwards;
    private String stringWithAmpersantAndMultipleDotsAfterwards;
    private String stringWithLettersAndSingleDashAndSingleApostropheAndSingleSpace;
    private String stringWithDoubleDash;
    private String stringWithDoubleApostrophe;
    private String stringWithDoubleSpace;
    private String stringWithDoubleSlash;
    private String stringWithLettersAndNumbersAndSingleDashAndSingleApostropheAndSingleSpace;
    private String stringWithLettersAndSingleDashAndSingleSpace;
    private String stringWithNumbers;
    private String stringWithLettersAndNumbersAndSingleDashAndSingleSlash;


    @BeforeEach
    void setUp() {
        yesterday = LocalDate.now().minusDays(1);
        today = LocalDate.now();
        tomorrow = LocalDate.now().plusDays(1);
        anHourAgo = LocalTime.now().minusHours(1);
        thisMoment = LocalTime.now();
        inAnHour = LocalTime.now().plusHours(1);
        charNumber = '1';
        charDash = '-';
        charApostrophe = '\'';
        charSpace = ' ';
        charSlash = '/';
        charLetter = 'A';
        stringNull = null;
        stringNotAllowedCharacters = "èàç!y!à";
        stringWithAmpersant = "bqfjl@ugoi";
        stringWithoutAmpersant = "gqsvsg";
        stringWithDot = "gs.sfdv";
        stringWithoutDot = "qgsf";
        stringWithAmpersantAndOneDotAfterwards = "qf@qgdf.qgsvdf";
        stringWithAmpersantAndMultipleDotsAfterwards = "gs@gesf.gztrgs.gzr";
        stringWithLettersAndSingleDashAndSingleApostropheAndSingleSpace = "fq-qf'qf qr";
        stringWithDoubleDash = "qdsf--fqf";
        stringWithDoubleApostrophe = "gfs''gqrg";
        stringWithDoubleSpace = "ggq  fqf";
        stringWithDoubleSlash = "gqfdg//qgf";
        stringWithLettersAndNumbersAndSingleDashAndSingleApostropheAndSingleSpace = "qgf1213-fqs'qfs qdsf";
        stringWithLettersAndSingleDashAndSingleSpace = "fgqq-gfdqsg sfdg";
        stringWithNumbers = "123";
        stringWithLettersAndNumbersAndSingleDashAndSingleSlash = "fqfq123-fqd/qdf";
    }

    @AfterEach
    void tearDown() {
        yesterday = null;
        today = null;
        tomorrow = null;
        anHourAgo = null;
        thisMoment = null;
        inAnHour = null;
        charNumber = null;
        charDash = null;
        charApostrophe = null;
        charSpace = null;
        charSlash = null;
        charLetter = null;
        stringNotAllowedCharacters = null;
        stringWithAmpersant = null;
        stringWithoutAmpersant = null;
        stringWithDot = null;
        stringWithoutDot = null;
        stringWithAmpersantAndOneDotAfterwards = null;
        stringWithAmpersantAndMultipleDotsAfterwards = null;
        stringWithLettersAndSingleDashAndSingleApostropheAndSingleSpace = null;
        stringWithDoubleDash = null;
        stringWithDoubleApostrophe = null;
        stringWithDoubleSpace = null;
        stringWithDoubleSlash = null;
        stringWithLettersAndNumbersAndSingleDashAndSingleApostropheAndSingleSpace = null;
        stringWithLettersAndSingleDashAndSingleSpace = null;
        stringWithNumbers = null;
        stringWithLettersAndNumbersAndSingleDashAndSingleSlash = null;
    }

    @Test
    void checkIfObjectIsNull_ObjectIsNull() {
        assertTrue(checkIfObjectIsNull(stringNull));
    }

    @Test
    void checkIfObjectIsNull_ObjectIsNotNull(){
        assertFalse(checkIfObjectIsNull(stringNotAllowedCharacters));
    }

    @Test
    void checkIfDateBeforeDate_DateIsBeforeOtherDate() {
        assertTrue(checkIfDateBeforeDate(yesterday, today));
    }

    @Test
    void checkIfDateBeforeDate_DateIsAfterOtherDate(){
        assertFalse(checkIfDateBeforeDate(tomorrow, today));
    }

    @Test
    void checkIfDateBeforeDate_DateIsOnSameDayAsOtherDate(){
        assertFalse(checkIfDateBeforeDate(today, today));
    }

    @Test
    void checkIfDateAfterDate_DateIsAfterOtherDate() {
        assertTrue(checkIfDateAfterDate(tomorrow, today));
    }

    @Test
    void checkIfDateAfterDate_DateIsBeforeOtherDate(){
        assertFalse(checkIfDateAfterDate(yesterday, today));
    }

    @Test
    void checkIfDateAfterDate_DateIsOnSameDayAsOtherDate(){
        assertFalse(checkIfDateAfterDate(today, today));
    }

    @Test
    void checkIfStringContainsAmpersant_StringContainsAmpersant() {
        assertTrue(checkIfStringContainsAmpersant(stringWithAmpersant));
    }

    @Test
    void checkIfStringContainsAmpersant_StringDoesNotContainAmpersant(){
        assertFalse(checkIfStringContainsAmpersant(stringWithoutAmpersant));
    }

    @Test
    void checkIfStringContainsDot_StringContainsDot() {
        assertTrue(checkIfStringContainsDot(stringWithDot));
    }

    @Test
    void checkIfStringContainsDot_StringDoesNotContainDot(){
        assertFalse(checkIfStringContainsDot(stringWithoutDot));
    }

    @Test
    void checkIfStringContainsAmpersantAndOnlyOneDotAfterAmpersant_StringContainsAmpersantAndOneDotAfterAmpersant() {
        assertTrue(checkIfStringContainsAmpersantAndOnlyOneDotAfterAmpersant(stringWithAmpersantAndOneDotAfterwards));
    }

    @Test
    void checkIfStringContainsAmpersantAndOnlyOneDotAfterAmpersant_StringContainsAmpersantAndMultipleDotsAfterAmpersant(){
        assertFalse(checkIfStringContainsAmpersantAndOnlyOneDotAfterAmpersant(stringWithAmpersantAndMultipleDotsAfterwards));
    }

    @Test
    void checkIfStringContainsAmpersantAndOnlyOneDotAfterAmpersant_StringContainsNoAmpersant(){
        assertFalse(checkIfStringContainsAmpersantAndOnlyOneDotAfterAmpersant(stringWithoutAmpersant));
    }

    @Test
    void checkIfDateIsToday_DateIsBeforeToday() {
        assertFalse(checkIfDateIsToday(yesterday));
    }

    @Test
    void checkIfDateIsToday_DateIsAfterToday(){
        assertFalse(checkIfDateIsToday(tomorrow));
    }

    @Test
    void checkIfDateIsToday_DateIsToday(){
        assertTrue(checkIfDateIsToday(today));
    }

    @Test
    void checkIfTmeIsAfterThisMoment_TimeIsBeforeThisMoment() {
        assertFalse(checkIfTmeIsAfterThisMoment(anHourAgo));
    }

    @Test
    void checkIfTmeIsAfterThisMoment_TimeIsAfterThisMoment(){
        assertTrue(checkIfTmeIsAfterThisMoment(inAnHour));
    }

    @Test
    void checkIfTmeIsAfterThisMoment_TimeIsThisMoment(){
        assertFalse(checkIfTmeIsAfterThisMoment(thisMoment));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces_StringContainsNotAllowedCharacters() {
        assertFalse(checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces(stringNotAllowedCharacters));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces_StringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces(){
        assertTrue(checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces(stringWithLettersAndSingleDashAndSingleApostropheAndSingleSpace));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces_StringContainsDoubleDash(){
        assertFalse(checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces(stringWithDoubleDash));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces_StringContainsDoubleApostrophe(){
        assertFalse(checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces(stringWithDoubleApostrophe));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces_StringContainsDoubleSpace(){
        assertFalse(checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces(stringWithDoubleSpace));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace_StringContainsNotAllowedCharacters() {
        assertFalse(checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace(stringNotAllowedCharacters));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace_StringContainsOnlyLettersAndOnlyNumbersAndSingleDashesAndSingleApostrophesAndSingleSpaces(){
        assertTrue(checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace(stringWithLettersAndNumbersAndSingleDashAndSingleApostropheAndSingleSpace));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace_StringContainsDoubleDash(){
        assertFalse(checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace(stringWithDoubleDash));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace_StringContainsDoubleApostrophe(){
        assertFalse(checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace(stringWithDoubleApostrophe));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace_StringContainsDoubleSpace(){
        assertFalse(checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace(stringWithDoubleSpace));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndSingleDashAndSingleSpace_StringContainsNotAllowedCharacters() {
        assertFalse(checkIfStringContainsOnlyLettersAndSingleDashAndSingleSpace(stringNotAllowedCharacters));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndSingleDashAndSingleSpace_StringContainsOnlyLettersAndSingleDashesAndSingleSpaces(){
        assertTrue(checkIfStringContainsOnlyLettersAndSingleDashAndSingleSpace(stringWithLettersAndSingleDashAndSingleSpace));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndSingleDashAndSingleSpace_StringContainsDoubleDash(){
        assertFalse(checkIfStringContainsOnlyLettersAndSingleDashAndSingleSpace(stringWithDoubleDash));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndSingleDashAndSingleSpace_StringContainsDoubleSpace(){
        assertFalse(checkIfStringContainsOnlyLettersAndSingleDashAndSingleSpace(stringWithDoubleSpace));
    }

    @Test
    void checkIfStringContainsOnlyNumbers_StringContainsNotAllowedCharacters() {
        assertFalse(checkIfStringContainsOnlyNumbers(stringNotAllowedCharacters));
    }

    @Test
    void checkIfStringContainsOnlyNumbers_StringContainsOnlyNumbers(){
        assertTrue(checkIfStringContainsOnlyNumbers(stringWithNumbers));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleSlash_StringContainsNotAllowedCharacters() {
        assertFalse(checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleSlash(stringNotAllowedCharacters));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleSlash_StringContainsOnlyLettersAndOnlyNumbersAndSingleDashesAndSingleSlashes(){
        assertTrue(checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleSlash(stringWithLettersAndNumbersAndSingleDashAndSingleSlash));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleSlash_StringContainsDoubleDash(){
        assertFalse(checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleSlash(stringWithDoubleDash));
    }

    @Test
    void checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleSlash_StringContainsDoubleSlash(){
        assertFalse(checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleSlash(stringWithDoubleSlash));
    }

    @Test
    void checkIfCharIsNumber_CharIsANumber() {
        assertTrue(checkIfCharIsNumber(charNumber));
    }

    @Test
    void checkIfCharIsNumber_CharIsNotANumber(){
        assertFalse(checkIfCharIsNumber(charDash));
    }

    @Test
    void checkIfCharIsDash_CharIsDash() {
        assertTrue(checkIfCharIsDash(charDash));
    }

    @Test
    void checkIfCharIsDash_CharIsNotADash(){
        assertFalse(checkIfCharIsDash(charNumber));
    }

    @Test
    void checkIfCharIsApostrophe_CharIsApostrophe() {
        assertTrue(checkIfCharIsApostrophe(charApostrophe));
    }

    @Test
    void checkIfCharIsApostrophe_CharIsNotAnApostrophe(){
        assertFalse(checkIfCharIsApostrophe(charNumber));
    }

    @Test
    void checkIfCharIsSpace_CharIsASpace() {
        assertTrue(checkIfCharIsSpace(charSpace));
    }

    @Test
    void checkIfCharIsSpace_CharIsNotASpace(){
        assertFalse(checkIfCharIsSpace(charNumber));
    }

    @Test
    void checkIfCharIsSlash_CharIsASlash() {
        assertTrue(checkIfCharIsSlash(charSlash));
    }

    @Test
    void checkIfCharIsSlash_CharIsNotASlash(){
        assertFalse(checkIfCharIsSlash(charNumber));
    }

    @Test
    void checkIfCharIsLetter_CharIsALetters() {
        assertTrue(checkIfCharIsLetter(charLetter));
    }

    @Test
    void checkIfCharIsLetter_CharIsNotALetter(){
        assertFalse(checkIfCharIsLetter(charNumber));
    }
}