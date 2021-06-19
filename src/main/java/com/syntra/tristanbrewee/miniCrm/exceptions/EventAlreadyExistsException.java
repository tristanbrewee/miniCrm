package com.syntra.tristanbrewee.miniCrm.exceptions;

import com.syntra.tristanbrewee.miniCrm.model.Event;

import java.util.List;

public class EventAlreadyExistsException extends CustomException{

    private Event event;

    public EventAlreadyExistsException(List<String> allErrors, Event event) {
        super(allErrors);
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
