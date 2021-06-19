package com.syntra.tristanbrewee.miniCrm.persistance.services;

import ch.qos.logback.core.joran.event.EndEvent;
import com.syntra.tristanbrewee.miniCrm.exceptions.EventAlreadyExistsException;
import com.syntra.tristanbrewee.miniCrm.exceptions.InvalidValueException;
import com.syntra.tristanbrewee.miniCrm.model.Event;
import com.syntra.tristanbrewee.miniCrm.persistance.repositories.EventRepository;
import com.syntra.tristanbrewee.miniCrm.utils.EventUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImp implements EventService{

    private EventRepository eventRepository;
    private CommunityServiceImp communityServiceImp;

    @Autowired
    public EventServiceImp(EventRepository eventRepository, CommunityServiceImp communityServiceImp) {
        this.eventRepository = eventRepository;
        this.communityServiceImp = communityServiceImp;
    }

    public void save(Event event){
        eventRepository.save(event);
    }

    public List<Event> findAll(){
        return eventRepository.findAll();
    }

    public List<Event> findByCommunityId(Integer communityId){
        return findAll().stream()
                .filter(e -> e.getCommunity().getCommunityId() == communityId)
                .collect(Collectors.toList());
    }

    public Event findById(Integer id){
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent())
            return event.get();
        else
            return null;
    }

    public void delete(Event event){
        eventRepository.delete(event);
    }

    public void updateCommunity(Integer eventId, Integer communityId){
        Event event = findById(eventId);
        event.setCommunity(communityServiceImp.findById(communityId).get());
        save(event);
    }

    public void checkIfEventAlreadyExists(Event event){
        List<Event> events = eventRepository.findAll()
                .stream()
                .filter(e -> e.getDescription().equals(event.getDescription()))
                .filter(e -> e.getEventDate().equals(event.getEventDate()))
                .filter(e -> e.getEventTime().equals(event.getEventTime()))
                .filter(e -> e.getCommunity().getCommunityId() == event.getCommunity().getCommunityId())
                .collect(Collectors.toList());
        if (!events.isEmpty())
            throw new EventAlreadyExistsException(null, event);
    }

    public void checkEventForErrors(Event event){
        List<String> errors = EventUtils.checkEventForInvalidInput(event);
        if (!errors.isEmpty())
            throw new InvalidValueException(errors);
    }

    public void normalizeEvent(Event event){
        if (event.getDescription().isEmpty())
            event.setDescription(null);
    }
}
