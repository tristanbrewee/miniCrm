package com.syntra.tristanbrewee.miniCrm.controllers;

import com.syntra.tristanbrewee.miniCrm.exceptions.EventAlreadyExistsException;
import com.syntra.tristanbrewee.miniCrm.exceptions.InvalidValueException;
import com.syntra.tristanbrewee.miniCrm.exceptions.PersonAlreadyExistsException;
import com.syntra.tristanbrewee.miniCrm.model.Community;
import com.syntra.tristanbrewee.miniCrm.model.Event;
import com.syntra.tristanbrewee.miniCrm.persistance.repositories.EventRepository;
import com.syntra.tristanbrewee.miniCrm.persistance.services.CommunityServiceImp;
import com.syntra.tristanbrewee.miniCrm.persistance.services.EventServiceImp;
import com.syntra.tristanbrewee.miniCrm.utils.Conversions;
import com.syntra.tristanbrewee.miniCrm.utils.DesktopApi;
import com.syntra.tristanbrewee.miniCrm.utils.PdfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EventController {

    private EventServiceImp eventServiceImp;
    private CommunityServiceImp communityServiceImp;
    private PdfUtils pdfUtils;

    @Autowired
    public EventController(EventServiceImp eventServiceImp, CommunityServiceImp communityServiceImp, PdfUtils pdfUtils) {
        this.eventServiceImp = eventServiceImp;
        this.communityServiceImp = communityServiceImp;
        this.pdfUtils = pdfUtils;
    }

    @GetMapping("/event")
    public String eventAll() {
        return "bootstrap/event/home";
    }

    @GetMapping("event/all")
    public String getAll(){
        return "bootstrap/event/allBeta";
    }

    @GetMapping("/event/specific/{id}")
    public String getSpecificEvent(@PathVariable String id, Model model) {
        Event specificEvent = eventServiceImp.findById(Integer.parseInt(id));
        model.addAttribute("specificEvent", specificEvent);
//        return "eventpages/specific";
        return "bootstrap/event/specificBeta";
    }

    @PostMapping("/event/updateEvent")
    public String updateEvent(@ModelAttribute Event specificEvent, @RequestParam("dateString") String dateString,
                              @RequestParam("timeString") String timeString,@RequestParam("communityDescription") String communityDescription) {
        updateOrCreateEvent(specificEvent, dateString, timeString, communityDescription);
        return "redirect:/event/specific/" + specificEvent.getEventId();
    }

    @PostMapping("/event/createLabels/{id}")
    public String createLabels(@PathVariable String id) {
        Event event = eventServiceImp.findById(Integer.parseInt(id));
        String filePath = pdfUtils.eventToAddressListPdf(event);
        if (filePath.startsWith("src")) {
            File file = new File(filePath);
            DesktopApi.open(file);
        } else {
            System.out.println("Don't know what happened here");
        }
        return "redirect:/event";
    }

    @GetMapping("/event/new")
    public String getNew(Model model){
        Event newEvent = new Event();
        model.addAttribute("newEvent", newEvent);
//        return "eventpages/new";
        return "bootstrap/event/newBeta";
    }

    @PostMapping("/event/createNewEvent")
    public String createNewEvent(@ModelAttribute Event newEvent, @RequestParam("dateString") String dateString,
                                 @RequestParam("timeString") String timeString, @RequestParam("communityString") String communityString){
        updateOrCreateEvent(newEvent, dateString, timeString, communityString);
        int eventId = eventServiceImp.findAll()
                .stream()
                .filter(e -> e.getDescription().equals(newEvent.getDescription()))
                .filter(e -> e.getCommunity().getDescription().equals(communityString))
                .filter(e -> e.getEventDate().toString().equals(Conversions.stringToLocalDate(dateString).toString()))
                .filter(e -> e.getEventTime().toString().equals(Conversions.stringToLocalTime(timeString).toString()))
                .collect(Collectors.toList())
                .get(0)
                .getEventId();
        return "redirect:/event/specific/" + eventId;
    }

    @GetMapping("event/delete/{id}")
    public String deleteEvent(@PathVariable String id){
        eventServiceImp.delete(eventServiceImp.findById(Integer.parseInt(id)));
        return "redirect:/event";
    }

    @ModelAttribute("events")
    public List<Event> eventList() {
        return eventServiceImp.findAll()
                .stream()
                .sorted(Comparator.comparing(Event::getEventDate)
                        .thenComparing(Event::getEventTime)
                        .thenComparing(Event::getDescription))
                .collect(Collectors.toList());
    }

    @ModelAttribute("communities")
    public List<Community> communityList() {
        return communityServiceImp.findAll();
    }

    private void updateOrCreateEvent(Event event, String dateString, String timeString, String communityString){
        event.setEventDate(Conversions.stringToLocalDate(dateString));
        event.setEventTime(Conversions.stringToLocalTime(timeString));
        event.setCommunity(communityServiceImp.findAll()
                .stream()
                .filter(e -> e.getDescription().equals(communityString))
                .collect(Collectors.toList())
                .get(0));
        eventServiceImp.normalizeEvent(event);
        eventServiceImp.checkIfEventAlreadyExists(event);
        eventServiceImp.checkEventForErrors(event);
        eventServiceImp.save(event);
    }

    @ExceptionHandler({EventAlreadyExistsException.class, InvalidValueException.class})
    public String handleExpectedException(Exception exception, Model model){
        if (exception instanceof InvalidValueException) {
            InvalidValueException invalidValueException = (InvalidValueException)exception;
            model.addAttribute("exception", invalidValueException);
            return "bootstrap/error/generalErrorPage";
        }
        else {
            EventAlreadyExistsException eventAlreadyExistsException = (EventAlreadyExistsException) exception;
            model.addAttribute("exception", eventAlreadyExistsException);
            return "bootstrap/error/eventErrorPage";
        }
    }

    @ExceptionHandler({Exception.class})
    public String handleUnexpectedException(Exception exception, Model model){
        model.addAttribute("exception", exception);
        exception.printStackTrace();
        return "bootstrap/error/unexpectedError";
    }
}
