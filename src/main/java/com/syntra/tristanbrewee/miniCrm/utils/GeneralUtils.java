package com.syntra.tristanbrewee.miniCrm.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class GeneralUtils {

    public static boolean checkIfObjectIsNull(Object o){
        return o == null;
    }

    public static boolean checkIfDateBeforeDate(LocalDate dateToCheck, LocalDate otherDate){
        return dateToCheck.isBefore(otherDate);
    }

    public static boolean checkIfDateAfterDate(LocalDate dateToCheck, LocalDate otherDate){
        return dateToCheck.isAfter(otherDate);
    }

    public static boolean checkIfStringContainsAmpersant(String stringToCheck){
        return stringToCheck.contains("@");
    }

    public static boolean checkIfStringContainsDot(String stringToCheck){
        return stringToCheck.contains(".");
    }

    public static boolean checkIfStringContainsAmpersantAndOnlyOneDotAfterAmpersant(String stringToCheck){
        if (checkIfStringContainsAmpersant(stringToCheck)){
            stringToCheck = stringToCheck.substring(stringToCheck.indexOf('@'));
            if (checkIfStringContainsDot(stringToCheck)){
                stringToCheck = stringToCheck.substring(stringToCheck.indexOf('.') + 1);
                return !checkIfStringContainsDot(stringToCheck);
            }
        }
        return false;
    }

    public static boolean checkIfDateIsToday(LocalDate dateToCheck){
        return dateToCheck.equals(LocalDate.now());
    }

    public static boolean checkIfTmeIsAfterThisMoment(LocalTime timeToCheck){
        return timeToCheck.isAfter(LocalTime.now());
    }

    public static boolean checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces(String string){
        boolean lastCharWasDash = false;
        boolean lastCharWasApostrophe = false;
        boolean lastCharWasSpace = false;
        string = string.toUpperCase();
        for (int i = 0; i < string.length(); i++){
            char x = string.charAt(i);
            if (checkIfCharIsLetter(x)) {
                lastCharWasDash = false;
                lastCharWasApostrophe = false;
                lastCharWasSpace = false;
                continue;
            }
            if (checkIfCharIsDash(x)){
                if (lastCharWasDash)
                    return false;
                lastCharWasDash = true;
                lastCharWasApostrophe = false;
                lastCharWasSpace = false;
                continue;
            }
            if (checkIfCharIsApostrophe(x)) {
                if (lastCharWasApostrophe)
                    return false;
                lastCharWasApostrophe = true;
                lastCharWasDash = false;
                lastCharWasSpace = false;
                continue;
            }
            if (checkIfCharIsSpace(x)){
                if (lastCharWasSpace)
                    return false;
                lastCharWasSpace = true;
                lastCharWasDash = false;
                lastCharWasApostrophe = false;
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace(String string){
        boolean lastCharWasDash = false;
        boolean lastCharWasApostrophe = false;
        boolean lastCharWasSpace = false;
        string = string.toUpperCase();
        for (int i = 0; i < string.length(); i++){
            char x = string.charAt(i);
            if (checkIfCharIsLetter(x)){
                lastCharWasDash = false;
                lastCharWasApostrophe = false;
                lastCharWasSpace = false;
                continue;
            }
            if (checkIfCharIsNumber(x)){
                lastCharWasDash = false;
                lastCharWasApostrophe = false;
                lastCharWasSpace = false;
                continue;
            }
            if (checkIfCharIsDash(x)){
                if (lastCharWasDash)
                    return false;
                lastCharWasDash = true;
                lastCharWasApostrophe = false;
                lastCharWasSpace = false;
                continue;
            }
            if (checkIfCharIsApostrophe(x)){
                if (lastCharWasApostrophe)
                    return false;
                lastCharWasApostrophe = true;
                lastCharWasDash = false;
                lastCharWasSpace = false;
                continue;
            }
            if (checkIfCharIsSpace(x)){
                if (lastCharWasSpace)
                    return false;
                lastCharWasSpace = true;
                lastCharWasDash = false;
                lastCharWasApostrophe = false;
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean checkIfStringContainsOnlyLettersAndSingleDashAndSingleSpace(String string){
        boolean lastCharWasDash = false;
        boolean lastCharWarSpace = false;
        string = string.toUpperCase();
        for (int i = 0; i < string.length(); i++){
            char x = string.charAt(i);
            if (checkIfCharIsLetter(x)){
                lastCharWasDash = false;
                lastCharWarSpace = false;
                continue;
            }
            if (checkIfCharIsDash(x)){
                if (lastCharWasDash)
                    return false;
                lastCharWasDash = true;
                lastCharWarSpace = false;
                continue;
            }
            if (checkIfCharIsSpace(x)){
                if (lastCharWarSpace)
                    return false;
                lastCharWarSpace = true;
                lastCharWasDash = false;
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean checkIfStringContainsOnlyNumbers(String string){
        for (int i = 0; i < string.length(); i++){
            char x =string.charAt(i);
            if (checkIfCharIsNumber(x))
                continue;
            return false;
        }
        return true;
    }

    public static boolean checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleSlash(String string){
        boolean lastCharWasDash = false;
        boolean lastCharWasSlash = false;
        string = string.toUpperCase();
        for (int i = 0; i < string.length(); i++){
            char x = string.charAt(i);
            if (checkIfCharIsLetter(x)){
                lastCharWasDash = false;
                lastCharWasSlash = false;
                continue;
            }
            if (checkIfCharIsNumber(x)){
                lastCharWasDash = false;
                lastCharWasSlash = false;
                continue;
            }
            if (checkIfCharIsDash(x)){
                if (lastCharWasDash)
                    return false;
                lastCharWasDash = true;
                lastCharWasSlash = false;
                continue;
            }
            if (checkIfCharIsSlash(x)){
                if (lastCharWasSlash)
                    return false;
                lastCharWasSlash = true;
                lastCharWasDash = false;
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean checkIfCharIsNumber(char x){
        try {
            Integer.parseInt(String.valueOf(x));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static boolean checkIfCharIsDash(char x){
        return x == 45;
    }

    public static boolean checkIfCharIsApostrophe(char x){
        return x == 39;
    }

    public static boolean checkIfCharIsSpace(char x){return x == 32;}

    public static boolean checkIfCharIsSlash(char x){return x == 47;}

    public static boolean checkIfCharIsLetter(char x){return (x >= 65 && x <= 90);}
}
