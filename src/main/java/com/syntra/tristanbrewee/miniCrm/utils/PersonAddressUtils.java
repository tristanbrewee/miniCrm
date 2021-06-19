package com.syntra.tristanbrewee.miniCrm.utils;

import com.syntra.tristanbrewee.miniCrm.model.PersonAddress;

import java.util.ArrayList;
import java.util.List;

public class PersonAddressUtils {

    public static List<String> checkPersonAddressForInvalidInput(PersonAddress personAddress){
        List<String> errors = new ArrayList<>();
        if (!emailContainsAmpersantAndOnlyOneDotAfterAmpersantIfNotEmpty(personAddress))
            errors.add("Email must contain '@' and only one '.' after that");
        if (!checkPhoneIsValid(personAddress))
            errors.add("Phone can only contain numbers");
        if (!checkMobileIsValid(personAddress))
            errors.add("Mobile can only contain numbers");
        return errors;
    }

    private static boolean emailContainsAmpersantAndOnlyOneDotAfterAmpersantIfNotEmpty(PersonAddress personAddress){
        if (personAddress.getEmail().isEmpty())
            return true;
        return GeneralUtils.checkIfStringContainsAmpersantAndOnlyOneDotAfterAmpersant(personAddress.getEmail());
    }

    private static boolean checkPhoneIsValid(PersonAddress personAddress){
        if (personAddress.getPhone().isEmpty())
            return true;
        return GeneralUtils.checkIfStringContainsOnlyNumbers(personAddress.getPhone());
    }

    private static boolean checkMobileIsValid(PersonAddress personAddress){
        if (personAddress.getMobile().isEmpty())
            return true;
        return GeneralUtils.checkIfStringContainsOnlyNumbers(personAddress.getMobile());
    }
}
