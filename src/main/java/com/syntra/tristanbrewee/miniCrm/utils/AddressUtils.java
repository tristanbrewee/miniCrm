package com.syntra.tristanbrewee.miniCrm.utils;

import com.syntra.tristanbrewee.miniCrm.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressUtils {

    public static List<String> checkIfAllFieldsHaveCorrectInputIfAnyFieldIsNotNull(Address address){
        List<String> errors = new ArrayList<>();
        if (checkIfAFieldIsNotNull(address)){
            errors = checkIfAllFieldsHaveCorrectInput(address);
        }
        return errors;
    }

    public static List<String> checkIfNoFieldIsNullExceptBusIfAnyFieldIsNotNull(Address address){
        List<String> errors = new ArrayList<>();
        if (checkIfAFieldIsNotNull(address)){
            errors = checkIfNoFieldIsNullExceptBus(address);
        }
        return errors;
    }

    public static boolean checkIfAllFieldsAreNull(Address address) {
        if (checkIfStreetIsNull(address))
            if (checkIfHouseNumberIsNull(address))
                if (checkIfBusIsNull(address))
                    if (checkIfCityIsNull(address))
                        if (checkIfZipIsNull(address))
                            if (checkIfCountryIsNull(address))
                                return true;
        return false;
    }

    private static boolean checkIfAFieldIsNotNull(Address address){
        if (!checkIfStreetIsNull(address))
            return true;
        if (!checkIfHouseNumberIsNull(address))
            return true;
        if (!checkIfBusIsNull(address))
            return true;
        if (!checkIfCityIsNull(address))
            return true;
        if (!checkIfZipIsNull(address))
            return true;
        if (!checkIfCountryIsNull(address))
            return true;
        return false;
    }

    private static List<String> checkIfNoFieldIsNullExceptBus(Address address){
        List<String> errors = new ArrayList<>();
        if (checkIfStreetIsNull(address))
            errors.add("Street can't be null if any value isn't null");
        if (checkIfHouseNumberIsNull(address))
            errors.add("House number can't be null if any value isn't null");
        if (checkIfCityIsNull(address))
            errors.add("City can't be null if any value isn't null");
        if (checkIfZipIsNull(address))
            errors.add("Zip can't be null if any value isn't null");
        if (checkIfCountryIsNull(address))
            errors.add("Country can't be null if any value isn't null");
        return errors;
    }

    private static List<String> checkIfAllFieldsHaveCorrectInput(Address address){
        List<String> errors = new ArrayList<>();
        if (!checkStreetIsValid(address))
            errors.add("Street can only contain letters, single dashes, and single spaces");
        if (!checkHouseNumberIsValid(address))
            errors.add("House number can only contain numbers");
        if (!checkBusIsValidIfNotNull(address))
            errors.add("Bus can only contain numbers, letters, single dashes, and single slashes");
        if (!checkCityIsValid(address))
            errors.add("City can only contain letters, single dashes, and single spaces");
        if (!checkZipIsValid(address))
            errors.add("Zip can only contain numbers");
        if (!checkCountryIsValid(address))
            errors.add("Country can only contain letters, single dashes, single apostrophes, and single spaces");
        return errors;
    }

    private static boolean checkIfStreetIsNull(Address address){
        return GeneralUtils.checkIfObjectIsNull(address.getStreet());
    }

    private static boolean checkIfHouseNumberIsNull(Address address){
        return GeneralUtils.checkIfObjectIsNull(address.getHouseNumber());
    }

    private static boolean checkIfBusIsNull(Address address){
        return GeneralUtils.checkIfObjectIsNull(address.getBus());
    }

    private static boolean checkIfCityIsNull(Address address){
        return GeneralUtils.checkIfObjectIsNull(address.getCity());
    }

    private static boolean checkIfZipIsNull(Address address){
        return GeneralUtils.checkIfObjectIsNull(address.getZip());
    }

    private static boolean checkIfCountryIsNull(Address address){
        return GeneralUtils.checkIfObjectIsNull(address.getCountry());
    }

    private static boolean checkHouseNumberIsValid(Address address){
        return GeneralUtils.checkIfStringContainsOnlyNumbers(address.getHouseNumber());
    }

    private static boolean checkZipIsValid(Address address){
        return GeneralUtils.checkIfStringContainsOnlyNumbers(address.getZip());
    }

    private static boolean checkStreetIsValid(Address address){
        return GeneralUtils.checkIfStringContainsOnlyLettersAndSingleDashAndSingleSpace(address.getStreet());
    }

    private static boolean checkBusIsValidIfNotNull(Address address){
        if (GeneralUtils.checkIfObjectIsNull(address.getBus()))
            return true;
        return GeneralUtils.checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleSlash(address.getBus());
    }

    private static boolean checkCityIsValid(Address address){
        return GeneralUtils.checkIfStringContainsOnlyLettersAndSingleDashAndSingleSpace(address.getCity());
    }

    private static boolean checkCountryIsValid(Address address){
        return GeneralUtils.checkIfStringContainsOnlyLettersAndSingleDashesAndSingleApostrophesAndSingleSpaces(address.getCountry());
    }
}
