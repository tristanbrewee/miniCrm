package com.syntra.tristanbrewee.miniCrm.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "community")
public class Community {

    @Id
    @Column(name = "community_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer communityId;

    @ManyToMany(mappedBy = "member")
    private List<Person> member;

    @OneToMany(mappedBy = "community")
    private List<Event> event;

    @Column(name = "description")
    private String description;

    //Constructors
    public Community(Integer communityId, String description) {
        this.communityId = communityId;
        this.description = description;
    }

    public Community(Community community){
        this(community.getCommunityId(), community.getDescription());
    }

    public Community() {
    }

    //Getters and Setters
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

    public List<Person> getMember() {
        return member;
    }

    public void setMember(List<Person> member) {
        this.member = member;
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }
}
