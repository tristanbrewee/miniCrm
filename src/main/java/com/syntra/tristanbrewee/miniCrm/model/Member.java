package com.syntra.tristanbrewee.miniCrm.model;

import com.syntra.tristanbrewee.miniCrm.model.idclasses.MemberId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "member")
public class Member {

    @EmbeddedId
    private MemberId memberId;

    private LocalDate since;
    private LocalDate until;

    //Constructors
    public Member(MemberId memberId, LocalDate since, LocalDate until) {
        this.memberId = memberId;
        this.since = since;
        this.until = until;
    }

    public Member(Member member){
        this(member.getMemberId(), member.getSince(), member.getUntil());
    }

    public Member() {
    }

    //Getters and Setter
    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId memberId) {
        this.memberId = memberId;
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
