package com.syntra.tristanbrewee.miniCrm.model.dtos;

import java.time.LocalDate;
import java.util.List;

public class CompletePerson {

    private Integer personId;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    private Boolean isActive;

    private List<CompleteAddress> completeAddress;
    private List<CompleteCommunity> completeCommunities;

    public CompletePerson(Integer personId, LocalDate birthDate, String firstName, String lastName, boolean isActive, List<CompleteAddress> completeAddress, List<CompleteCommunity> completeCommunities) {
        this.personId = personId;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.completeAddress = completeAddress;
        this.completeCommunities = completeCommunities;
    }

    public CompletePerson() {
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public List<CompleteAddress> getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(List<CompleteAddress> completeAddress) {
        this.completeAddress = completeAddress;
    }

    public List<CompleteCommunity> getCompleteCommunities() {
        return completeCommunities;
    }

    public void setCompleteCommunities(List<CompleteCommunity> completeCommunities) {
        this.completeCommunities = completeCommunities;
    }
}
