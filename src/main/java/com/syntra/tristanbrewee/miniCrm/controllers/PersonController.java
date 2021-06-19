package com.syntra.tristanbrewee.miniCrm.controllers;

import com.syntra.tristanbrewee.miniCrm.exceptions.InvalidValueException;
import com.syntra.tristanbrewee.miniCrm.exceptions.PersonAlreadyExistsException;
import com.syntra.tristanbrewee.miniCrm.model.Member;
import com.syntra.tristanbrewee.miniCrm.model.Person;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompletePerson;
import com.syntra.tristanbrewee.miniCrm.model.enums.TypePossibilities;
import com.syntra.tristanbrewee.miniCrm.persistance.services.*;
import com.syntra.tristanbrewee.miniCrm.utils.Conversions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PersonController {

    private PersonServiceImp personServiceImp;
    private PersonAddressServiceImp personAddressServiceImp;
    private CompleteClassesServiceImp completeClassesServiceImp;
    private MemberServiceImp memberServiceImp;
    private AddressServiceImp addressServiceImp;
    private CommunityServiceImp communityServiceImp;

    @Autowired
    public PersonController(PersonServiceImp personServiceImp, PersonAddressServiceImp personAddressServiceImp, CompleteClassesServiceImp completeClassesServiceImp, MemberServiceImp memberServiceImp, AddressServiceImp addressServiceImp, CommunityServiceImp communityServiceImp){
        this.personServiceImp = personServiceImp;
        this.personAddressServiceImp = personAddressServiceImp;
        this.completeClassesServiceImp = completeClassesServiceImp;
        this.memberServiceImp = memberServiceImp;
        this.addressServiceImp = addressServiceImp;
        this.communityServiceImp = communityServiceImp;
    }

    @GetMapping("/person")
    public String personHome(){
        return "bootstrap/person/home";
    }

    @GetMapping("/person/all")
    public String personAll(Model model){
        List<Person> personListByActive = personServiceImp.findAllByActive();
        model.addAttribute("personListByActive", personListByActive);
//        return "personpages/all";
        return "bootstrap/person/allBeta";
    }

    @GetMapping("/person/specific/{id}")
    public String getSpecific(@PathVariable String id, Model model){
        model.addAttribute("specificPerson", completeClassesServiceImp.getByPersonId(Integer.parseInt(id)));
//        return "personpages/specific";
        return "bootstrap/person/specificBeta";
    }

    @PostMapping("/person/update")
    public String updateCompletePerson(@ModelAttribute CompletePerson specificPerson, @RequestParam("birthDateString") String birthDateString){
        specificPerson.setBirthDate(Conversions.stringToLocalDate(birthDateString));
        completeClassesServiceImp.saveCompletePersonAll(specificPerson);
        return "redirect:/person/specific/"+specificPerson.getPersonId();
    }

    @PostMapping("/person/delete")
    public String deleteCompletePerson(@ModelAttribute CompletePerson specificPerson){
        Integer id = specificPerson.getPersonId();
        personServiceImp.delete(id);
        return "redirect:/person/all";
    }

    @GetMapping("/person/delete/{id}")
    public String deletePerson(@PathVariable String id){
        Integer idInt = Integer.parseInt(id);
        personServiceImp.delete(idInt);
        return "redirect:/person/all";
    }

    @PostMapping("/person/savePerson")
    public String updatePerson(@ModelAttribute CompletePerson specificPerson, @RequestParam("birthDateString") String birthDateString){
        specificPerson.setBirthDate(Conversions.stringToLocalDate(birthDateString));
        completeClassesServiceImp.saveCompletePersonPersonalInfo(specificPerson);
        return "redirect:/person/specific/"+specificPerson.getPersonId();
    }

    @PostMapping("/person/addAddress")
    public String addAddress(@ModelAttribute CompletePerson specificPerson, Model model){
        model.addAttribute("specificPerson", specificPerson);
        return "bootstrap/address/newBeta";
    }

    @PostMapping("/person/saveAddress")
    public String editAddress(@ModelAttribute CompletePerson specificPerson, HttpServletRequest httpServletRequest){
        Iterator iterator = httpServletRequest.getParameterNames().asIterator();
        Integer index = -1;
        while (iterator.hasNext()){
            String temp = iterator.next().toString();
            if (temp.startsWith("saveAddress")){
                index = Integer.parseInt(temp.substring("saveAddress".length()));
                break;
            }
        }
        String type = httpServletRequest.getParameter("type" + index);
        specificPerson.getCompleteAddress().get(index).setType(type);
        completeClassesServiceImp.updateAddressOfCompletePerson(specificPerson, index);
        return "redirect:/person/specific/" + specificPerson.getPersonId();
    }

    @PostMapping("/person/deleteAddress")
    public String deleteAddress(@ModelAttribute CompletePerson specificPerson, HttpServletRequest httpServletRequest){
        Iterator iterator = httpServletRequest.getParameterNames().asIterator();
        Integer index = -1;
        while (iterator.hasNext()){
            String temp = iterator.next().toString();
            if (temp.startsWith("deleteAddress")){
                index = Integer.parseInt(temp.substring("deleteAddress".length()));
                break;
            }
        }
        completeClassesServiceImp.deleteAddressOfCompletePerson(specificPerson, index);
        return "redirect:/person/specific/"+specificPerson.getPersonId();
    }

    @PostMapping("/person/addCommunity")
    public String addCommunity(@ModelAttribute CompletePerson specificPerson, Model model){
        model.addAttribute("specificPerson", specificPerson);
        model.addAttribute("communities", communityServiceImp.findAll());
        List<Integer> communitiesOfPerson = completeClassesServiceImp.getAllCommunityIds(specificPerson);
        model.addAttribute("communitiesOfPerson", communitiesOfPerson);
        return "bootstrap/community/selectBeta";
    }

    @PostMapping("/person/deleteCommunity")
    public String deleteCommunity(@ModelAttribute CompletePerson specificPerson, HttpServletRequest httpServletRequest){
        List<Member> members = Conversions.completePersonToMemberList(specificPerson);
        Iterator iterator = httpServletRequest.getParameterNames().asIterator();
        Integer index = -1;
        while (iterator.hasNext()){
            String temp = iterator.next().toString();
            if (temp.startsWith("deleteCommunity")) {
                index = Integer.parseInt(temp.substring(temp.length() - 1));
                break;
            }
        }
        members.get(index).setUntil(LocalDate.now());
        memberServiceImp.saveMember(members.get(index));
        return "redirect:/person/specific/" + specificPerson.getPersonId();
    }

    @GetMapping("/person/activate")
    public String getActivate(Model model){
        List<Person> personsByInactive = personServiceImp.findAllByInactive();
        model.addAttribute("personsByInactive", personsByInactive);
//        return "personpages/activate";
        return "bootstrap/person/activateBeta";
    }

    @GetMapping("/person/activate/{id}")
    public String activatePerson(@PathVariable String id){
        personServiceImp.activatePersonById(Integer.parseInt(id));
        personServiceImp.savePerson(personServiceImp.findById(Integer.parseInt(id)).get());
        return "redirect:/person/activate";
    }

    @GetMapping("/person/newPerson")
    public String getNew(Model model){
        model.addAttribute("newPerson", new Person());
//        return "personpages/new";
        return "bootstrap/person/newBeta";
    }

    @PostMapping("/person/createNewPerson")
    public String createNewPerson(@ModelAttribute Person newPerson, @RequestParam("birthDateString") String birthDateString){
        newPerson.setActive(true);
        newPerson.setBirthDate(Conversions.stringToLocalDate(birthDateString));
        personServiceImp.createNewPerson(newPerson);
        int id = personServiceImp.findAll()
                .stream()
                .filter(e -> e.getLastName().equals(newPerson.getLastName()))
                .filter(e -> e.getFirstName().equals(newPerson.getFirstName()))
                .filter(e -> e.getBirthDate().equals(newPerson.getBirthDate()))
                .collect(Collectors.toList())
                .get(0)
                .getPersonId();
        return "redirect:/person/specific/" + id;
    }

    @ModelAttribute("completePersonsActive")
    public List<CompletePerson> completePersonListActive(){
        return completeClassesServiceImp.getAllCompletePersonsByActive();
    }

    @ExceptionHandler({PersonAlreadyExistsException.class, InvalidValueException.class})
    public String handleExpectedException(Exception exception, Model model){
        if (exception instanceof InvalidValueException) {
            InvalidValueException invalidValueException = (InvalidValueException)exception;
            model.addAttribute("exception", invalidValueException);
            return "bootstrap/error/generalErrorPage";
        }
        else {
            PersonAlreadyExistsException personAlreadyExistsException = (PersonAlreadyExistsException)exception;
            model.addAttribute("exception", personAlreadyExistsException);
            return "bootstrap/error/personErrorPage";
        }
    }

    @ExceptionHandler({Exception.class})
    public String handleUnexpectedException(Exception exception, Model model){
        model.addAttribute("exception", exception);
        exception.printStackTrace();
        return "bootstrap/error/unexpectedError";
    }
}
