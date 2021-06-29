package com.syntra.tristanbrewee.miniCrm.utils;

import static org.junit.Assert.*;
import static com.syntra.tristanbrewee.miniCrm.utils.EventUtils.*;

import com.syntra.tristanbrewee.miniCrm.model.Community;
import com.syntra.tristanbrewee.miniCrm.model.Event;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TestEventUtils {

    private String descriptionNull;
    private String descriptionInvalid;
    private String descriptionValid;
    private LocalDate tomorrow;
    private LocalDate today;
    private LocalDate yesterday;
    private LocalTime now;
    private LocalTime oneHourAgo;
    private LocalTime inOneHour;
    private final Community community = new Community(1, "com");

    private String errorDescriptionNull;
    private String errorDescriptionInvalid;
    private String errorDateBeforeToday;
    private String errorTimeBeforeNow;

    @Before
    public void startUp(){
        descriptionNull = null;
        descriptionInvalid = ",;:,;";
        descriptionValid = "valid description";
        tomorrow = LocalDate.now().plusDays(1);
        today = LocalDate.now();
        yesterday = LocalDate.now().minusDays(1);
        now = LocalTime.now();
        oneHourAgo = LocalTime.now().minusHours(1);
        inOneHour = LocalTime.now().plusHours(1);

        errorDescriptionNull = "Description can't be null";
        errorDescriptionInvalid = "Description can only contain letters, numbers, single dashes, single apostrophes, and single spaces";
        errorDateBeforeToday = "Event date can't be before today";
        errorTimeBeforeNow = "If event date is today, then event time must be after this moment";
    }

    @Test
    public void testCheckEventForInvalidInput(){
        Event event = new Event(1, community, tomorrow, now, descriptionValid);
        List<String> errors = checkEventForInvalidInput(event);
        errors.stream().forEach(System.out::println);
        assertEquals(0, errors.size());

        event.setDescription(descriptionNull);
        errors = checkEventForInvalidInput(event);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorDescriptionNull));

        event.setDescription(descriptionInvalid);
        errors = checkEventForInvalidInput(event);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorDescriptionInvalid));

        event.setDescription(descriptionValid);
        event.setEventDate(yesterday);
        errors = checkEventForInvalidInput(event);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorDateBeforeToday));

        event.setEventDate(today);
        event.setEventTime(oneHourAgo);
        errors = checkEventForInvalidInput(event);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorTimeBeforeNow));

        event.setEventTime(inOneHour);
        errors = checkEventForInvalidInput(event);
        assertEquals(0, errors.size());
    }
}
