package com.syntra.tristanbrewee.miniCrm.exceptions;

import com.syntra.tristanbrewee.miniCrm.model.Person;

import java.util.List;

public class PersonAlreadyExistsException extends CustomException{

    private Person person;

    public PersonAlreadyExistsException(List<String> allErrors, Person person) {
        super(allErrors);
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
