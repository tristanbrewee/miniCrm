package com.syntra.tristanbrewee.miniCrm.model.dtos;

import java.time.LocalDate;

public class CompleteMember {

    private Integer personId;
    private String lastName;
    private String firstName;
    private LocalDate since;
    private LocalDate until;

    public CompleteMember(Integer personId, String lastName, String firstName, LocalDate since, LocalDate until) {
        this.personId = personId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.since = since;
        this.until = until;
    }

    public CompleteMember() {
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getSince() {
        return since;
    }

    public void setSince(LocalDate since) {
        this.since = since;
    }

    public LocalDate getUntil() {
        return until;
    }

    public void setUntil(LocalDate until) {
        this.until = until;
    }
}
