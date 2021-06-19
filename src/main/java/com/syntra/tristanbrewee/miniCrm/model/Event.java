package com.syntra.tristanbrewee.miniCrm.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "event_time")
    private LocalTime eventTime;

    @Column(name = "description")
    private String description;

    //Constructors
    public Event(Integer eventId, Community community, LocalDate eventDate, LocalTime eventTime, String description) {
        this.eventId = eventId;
        this.community = community;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.description = description;
    }

    public Event(Event event){
        this(event.getEventId(), event.getCommunity(), event.getEventDate(), event.getEventTime(), event.getDescription());
    }

    public Event() {
    }

    //Getters and Setters
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
