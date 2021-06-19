package com.syntra.tristanbrewee.miniCrm.utils;

import com.syntra.tristanbrewee.miniCrm.model.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventUtils {

    public static List<String> checkEventForInvalidInput(Event event){
        List<String> errors = new ArrayList<>();
        if (checkIfDescriptionIsNull(event))
            errors.add("Description can't be null");
        else if (!checkDescriptionIsValid(event))
            errors.add("Description can only contain letters, numbers, single dashes, single apostrophes, and single spaces");
        if (!checkIfEventDateIsAfterYesterday(event))
            errors.add("Event date can't be before today");
        else if (!checkIfEventTimeIsAfterThisMomentIfDateIsToday(event))
            errors.add("If event date is today, then event time must be after this moment");
        return errors;
    }

    private static boolean checkDescriptionIsValid(Event event){
        return GeneralUtils.checkIfStringContainsOnlyLettersAndOnlyNumbersAndSingleDashAndSingleApostropheAndSingleSpace(event.getDescription());
    }

    private static boolean checkIfDescriptionIsNull(Event event){
        return GeneralUtils.checkIfObjectIsNull(event.getDescription());
    }

    private static boolean checkIfEventDateIsAfterYesterday(Event event){
        return GeneralUtils.checkIfDateAfterDate(event.getEventDate(), LocalDate.now().minusDays(1));
    }

    private static boolean checkIfEventTimeIsAfterThisMomentIfDateIsToday(Event event){
        if (GeneralUtils.checkIfDateIsToday(event.getEventDate()))
            return GeneralUtils.checkIfTmeIsAfterThisMoment(event.getEventTime());
        return true;
    }
}
