package com.syntra.tristanbrewee.miniCrm.controllers;

import com.syntra.tristanbrewee.miniCrm.exceptions.CommunityAlreadyExistsException;
import com.syntra.tristanbrewee.miniCrm.exceptions.InvalidValueException;
import com.syntra.tristanbrewee.miniCrm.exceptions.PersonAlreadyExistsException;
import com.syntra.tristanbrewee.miniCrm.model.Community;
import com.syntra.tristanbrewee.miniCrm.model.Event;
import com.syntra.tristanbrewee.miniCrm.model.Member;
import com.syntra.tristanbrewee.miniCrm.model.Person;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompleteMember;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompletePerson;
import com.syntra.tristanbrewee.miniCrm.persistance.repositories.CommunityRepository;
import com.syntra.tristanbrewee.miniCrm.persistance.services.*;
import com.syntra.tristanbrewee.miniCrm.utils.CommunityUtils;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CommunityController {

    private CommunityServiceImp communityServiceImp;
    private CompleteClassesServiceImp completeClassesServiceImp;
    private MemberServiceImp memberServiceImp;
    private EventServiceImp eventServiceImp;
    private PersonServiceImp personServiceImp;

    @Autowired
    public CommunityController(CommunityServiceImp communityServiceImp, CompleteClassesServiceImp completeClassesServiceImp,
                               MemberServiceImp memberServiceImp, EventServiceImp eventServiceImp, PersonServiceImp personServiceImp) {
        this.communityServiceImp = communityServiceImp;
        this.completeClassesServiceImp = completeClassesServiceImp;
        this.memberServiceImp = memberServiceImp;
        this.eventServiceImp = eventServiceImp;
        this.personServiceImp = personServiceImp;
    }

    @GetMapping("/community")
    public String communityAll(){
//        return "communitypages/all";
//        return "bootstrap/community/all";
        return "bootstrap/community/home";
    }

    @GetMapping("/community/all")
    public String getAll(){
        return "bootstrap/community/allBeta";
    }

    @PostMapping("/community/addToPerson")
    public String addToPerson(@ModelAttribute CompletePerson specificPerson, HttpServletRequest httpServletRequest){
        Iterator iterator = httpServletRequest.getParameterNames().asIterator();
        Integer index = -1;
        while (iterator.hasNext()){
            String temp = iterator.next().toString();
            if (temp.startsWith("addCommunity")){
                index = Integer.parseInt(temp.substring("addCommunity".length()));
                break;
            }
        }
        completeClassesServiceImp.addCommunityToCompletePerson(specificPerson, index);
        return "redirect:/person/specific/" + specificPerson.getPersonId();
    }

    @GetMapping("/community/specific/{id}")
    public String getSpecific(@PathVariable String id, Model model){
        Community specificCommunity = communityServiceImp.findById(Integer.parseInt(id)).get();
        List<CompleteMember> completeMembers = completeClassesServiceImp.getCompleteMembersByEvent(Integer.parseInt(id));
        List<Event> events = eventServiceImp.findByCommunityId(Integer.parseInt(id));
        model.addAttribute("specificCommunity", specificCommunity);
        model.addAttribute("completeMembers", completeMembers);
        model.addAttribute("events", events);
//        return "communitypages/specific";
        return "bootstrap/community/specificBeta";
    }

    @GetMapping("/community/delete/{id}")
    public String deleteCommunity(@PathVariable String id){
        Optional<Community> communityToDelete = communityServiceImp.findById(Integer.parseInt(id));
        if (communityToDelete.isPresent())
            communityServiceImp.delete(communityToDelete.get());
        return "redirect:/community";
    }

    @PostMapping("/community/addNew")
    public String addNewCommunity(@ModelAttribute Community newCommunity){
        checkCommunityForErrors(newCommunity);
        List<Integer> allIndexes = communityServiceImp.findAll()
                .stream()
                .mapToInt(e -> e.getCommunityId())
                .boxed()
                .sorted()
                .collect(Collectors.toList());
        Integer newIndex = allIndexes.get(allIndexes.size() - 1) + 1;
        newCommunity.setCommunityId(newIndex);
        communityServiceImp.save(newCommunity);
        return "redirect:/community/specific/" + newCommunity.getCommunityId().toString();
    }

    @GetMapping("/community/subscribePerson/{id}")
    public String subscribePerson(@PathVariable String id){
        Integer personId = Integer.parseInt(id.substring(0, id.indexOf(':')));
        Integer communityId = Integer.parseInt(id.substring(id.indexOf(':') + 1));
        memberServiceImp.reSubscribePersonByCommunityIdAndPersonId(communityId, personId);
        return "redirect:/community/specific/" + communityId;
    }

    @GetMapping("/community/unsubscribePerson/{id}")
    public String unsubscribePerson(@PathVariable String id){
        Integer personId = Integer.parseInt(id.substring(0, id.indexOf(':')));
        Integer communityId = Integer.parseInt(id.substring(id.indexOf(':') + 1));
       memberServiceImp.unsubscribePersonByCommunityIdAndPersonId(communityId, personId);
       return "redirect:/community/specific/" + communityId;
    }

    @GetMapping("/community/getAddNewPersons/{id}")
    public String getAddNewPerson(@PathVariable String id, Model model){
        Optional<Community> specificCommunity = communityServiceImp.findById(Integer.parseInt(id));
        if (specificCommunity.isPresent()){
            model.addAttribute("specificCommunity", specificCommunity.get());
            List<Integer> currentMembersByPersonId = memberServiceImp.getByCommunityId(specificCommunity.get().getCommunityId())
                    .stream()
                    .mapToInt(e -> e.getMemberId().getPerson_id())
                    .boxed()
                    .collect(Collectors.toList());
            List<Person> notAddedPersons = personServiceImp.findAll()
                    .stream()
                    .filter(Person::getActive)
                    .filter(e -> !currentMembersByPersonId.contains(e.getPersonId()))
                    .sorted(
                            Comparator.comparing(Person::getLastName)
                            .thenComparing(Person::getFirstName)
                            .thenComparing(Person::getBirthDate)
                    )
                    .collect(Collectors.toList());
            model.addAttribute("notAddedPersons", notAddedPersons);
//            return "communitypages/addNewPersons";
            return "bootstrap/community/addNewPersonsBeta";
        }
        return "redirect:/community";
    }

    @GetMapping("/community/addPerson/{id}")
    public String addPersonToCommunity(@PathVariable String id){
        Integer communityId = Integer.parseInt(id.substring(0, id.indexOf(':')));
        Integer personId = Integer.parseInt(id.substring(id.indexOf(':') + 1));
        memberServiceImp.subscribePersonByCommunityIdAndPersonId(communityId, personId);
        return "redirect:/community/getAddNewPersons/" + communityId;
    }

    @GetMapping("/community/new")
    public String getNew(Model model){
        model.addAttribute("newCommunity", new Community());
        return "bootstrap/community/newBeta";
    }

    @ModelAttribute("communities")
    public List<Community> communityList(){
        return communityServiceImp.findAll()
                .stream()
                .sorted(Comparator.comparing(Community::getDescription))
                .collect(Collectors.toList());
    }

    private boolean checkIfCommunityAlreadyExists(Community community){
        return communityServiceImp.findAll()
                .stream()
                .map(e -> e.getDescription())
                .collect(Collectors.toList())
                .contains(community.getDescription());
    }

    private void checkCommunityForErrors(Community community){
        if (checkIfCommunityAlreadyExists(community))
            throw new CommunityAlreadyExistsException(null, community);
        List<String> errors = CommunityUtils.checkIfCommunityHasCorrectValues(community);
        if (!errors.isEmpty())
            throw new InvalidValueException(errors);
    }

    @ExceptionHandler({CommunityAlreadyExistsException.class, InvalidValueException.class})
    public String handleExpectedException(Exception exception, Model model){
        if (exception instanceof InvalidValueException) {
            InvalidValueException invalidValueException = (InvalidValueException)exception;
            model.addAttribute("exception", invalidValueException);
            return "bootstrap/error/generalErrorPage";
        }
        else {
            CommunityAlreadyExistsException communityAlreadyExistsException = (CommunityAlreadyExistsException) exception;
            model.addAttribute("exception", communityAlreadyExistsException);
            return "bootstrap/error/communityErrorPage";
        }
    }

    @ExceptionHandler({Exception.class})
    public String handleUnexpectedException(Exception exception, Model model){
        model.addAttribute("exception", exception);
        exception.printStackTrace();
        return "bootstrap/error/unexpectedError";
    }
}
