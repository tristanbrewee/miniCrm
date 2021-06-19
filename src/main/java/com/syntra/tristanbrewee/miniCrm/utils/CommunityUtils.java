package com.syntra.tristanbrewee.miniCrm.utils;

import com.syntra.tristanbrewee.miniCrm.model.Community;

import java.util.ArrayList;
import java.util.List;

public class CommunityUtils {

    public static List<String> checkIfCommunityHasCorrectValues(Community community){
        List<String> errors = new ArrayList<>();
        if (checkIfDescriptionIsNull(community))
            errors.add("Description can't be null");
        else if (!checkDescriptionIsValid(community))
            errors.add("Description can only contain letters, numbers, single dashes, single apostrophes, and single spaces");
        return errors;
    }

    private static boolean checkIfDescriptionIsNull(Community community){
        return GeneralUtils.checkIfObjectIsNull(community.getDescription());
    }

    private static boolean checkDescriptionIsValid(Community community){
        return GeneralUtils.checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace(community.getDescription());
    }
}
