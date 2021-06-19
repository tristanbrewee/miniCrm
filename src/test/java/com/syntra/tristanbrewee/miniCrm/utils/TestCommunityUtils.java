package com.syntra.tristanbrewee.miniCrm.utils;

import static org.junit.Assert.*;
import static com.syntra.tristanbrewee.miniCrm.utils.CommunityUtils.*;

import com.syntra.tristanbrewee.miniCrm.model.Community;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestCommunityUtils {

    private String validDescription;
    private String invalidDescription;
    private String nullString;

    private String errorDescriptionNull;
    private String errorDescriptionInvalid;

    @Before
    public void startUp(){
        validDescription = "valid";
        invalidDescription = ";:=;=:";
        nullString = null;
        errorDescriptionNull = "Description can't be null";
        errorDescriptionInvalid = "Description can only contain numbers and letters";
    }

    @Test
    public void testCheckIfCommunityHasCorrectValues(){
        Community community = new Community(1, nullString);
        List<String> errors = checkIfCommunityHasCorrectValues(community);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorDescriptionNull));
        community.setDescription(invalidDescription);
        errors = checkIfCommunityHasCorrectValues(community);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorDescriptionInvalid));
        community.setDescription(validDescription);
        errors = checkIfCommunityHasCorrectValues(community);
        assertEquals(0, errors.size());
    }
}
