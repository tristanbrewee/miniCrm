package com.syntra.tristanbrewee.miniCrm.exceptions;

import java.util.List;

public class InvalidValueException extends CustomException{

    public InvalidValueException(List<String> allErrors) {
        super(allErrors);
    }
}
