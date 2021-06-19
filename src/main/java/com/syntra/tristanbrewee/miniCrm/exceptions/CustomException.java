package com.syntra.tristanbrewee.miniCrm.exceptions;

import java.util.List;

public class CustomException extends RuntimeException{

    private List<String> allErrors;

    public CustomException(List<String> allErrors) {
        this.allErrors = allErrors;
    }

    public List<String> getAllErrors() {
        return allErrors;
    }

    public void setAllErrors(List<String> allErrors) {
        this.allErrors = allErrors;
    }
}
