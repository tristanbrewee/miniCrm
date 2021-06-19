package com.syntra.tristanbrewee.miniCrm.model.idclasses;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MemberId implements Serializable {

    @Column
    private Integer community_id;
    @Column
    private Integer person_id;

    //Constructors
    public MemberId(Integer community_id, Integer person_id) {
        this.community_id = community_id;
        this.person_id = person_id;
    }

    public MemberId(){}

    //Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberId memberId = (MemberId) o;
        return Objects.equals(community_id, memberId.community_id) && Objects.equals(person_id, memberId.person_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(community_id, person_id);
    }

    //Getters and Setters
    public Integer getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(Integer communityId) {
        this.community_id = communityId;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }
}
