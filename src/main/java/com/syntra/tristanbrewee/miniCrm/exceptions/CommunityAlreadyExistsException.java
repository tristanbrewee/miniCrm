package com.syntra.tristanbrewee.miniCrm.exceptions;

import com.syntra.tristanbrewee.miniCrm.model.Community;

import java.util.List;

public class CommunityAlreadyExistsException extends CustomException{

    private Community community;

    public CommunityAlreadyExistsException(List<String> allErrors, Community community) {
        super(allErrors);
        this.community = community;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
