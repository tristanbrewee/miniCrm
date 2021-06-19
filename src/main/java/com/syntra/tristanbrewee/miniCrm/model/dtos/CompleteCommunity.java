package com.syntra.tristanbrewee.miniCrm.model.dtos;

import java.time.LocalDate;

public class CompleteCommunity {

    private Integer communityId;
    private String description;

    private LocalDate since;
    private LocalDate until;

    public CompleteCommunity(Integer communityId, String description, LocalDate since, LocalDate until) {
        this.communityId = communityId;
        this.description = description;
        this.since = since;
        this.until = until;
    }

    public CompleteCommunity() {
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
