package com.syntra.tristanbrewee.miniCrm.utils;

import com.syntra.tristanbrewee.miniCrm.model.PersonAddress;
import com.syntra.tristanbrewee.miniCrm.model.idclasses.PersonAddressId;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static com.syntra.tristanbrewee.miniCrm.utils.PersonAddressUtils.*;

public class TestPersonAddressUtils {

    private String emailValid;
    private String emailInvalid;
    private String phoneValid;
    private String phoneInvalid;
    private String mobileValid;
    private String mobileInvalid;
    private final String type = "work";
    private PersonAddress personAddress;

    private String errorEmail;
    private String errorPhone;
    private String errorMobile;

    @Before
    public void setUp(){
        emailValid = "test.test@test.test";
        emailInvalid = "test.test@test";
        phoneValid = "123";
        phoneInvalid = "dsg";
        mobileValid = "123";
        mobileInvalid = "sdg";

        personAddress = new PersonAddress();
        personAddress.setPersonAddressId(new PersonAddressId(1, 1));
        personAddress.setEmail(emailValid);
        personAddress.setPhone(phoneValid);
        personAddress.setMobile(mobileValid);
        personAddress.setType(type);

        errorEmail = "Email must contain '@' and only one '.' after that";
        errorPhone = "Phone can only contain numbers";
        errorMobile = "Mobile can only contain numbers";
    }

    @Test
    public void testCheckPersonAddressForInvalidInput(){
        List<String> errors = checkPersonAddressForInvalidInput(personAddress);
        assertEquals(0, errors.size());

        personAddress.setEmail(emailInvalid);
        errors = checkPersonAddressForInvalidInput(personAddress);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorEmail));

        personAddress.setEmail(emailValid);
        personAddress.setPhone(phoneInvalid);
        errors = checkPersonAddressForInvalidInput(personAddress);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorPhone));

        personAddress.setPhone(phoneValid);
        personAddress.setMobile(mobileInvalid);
        errors = checkPersonAddressForInvalidInput(personAddress);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(errorMobile));
    }
}
