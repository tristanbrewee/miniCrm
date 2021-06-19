package com.syntra.tristanbrewee.miniCrm.controllers;

import com.syntra.tristanbrewee.miniCrm.exceptions.InvalidValueException;
import com.syntra.tristanbrewee.miniCrm.exceptions.PersonAlreadyExistsException;
import com.syntra.tristanbrewee.miniCrm.model.Address;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompleteAddress;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompletePerson;
import com.syntra.tristanbrewee.miniCrm.persistance.repositories.AddressRepository;
import com.syntra.tristanbrewee.miniCrm.persistance.services.CompleteClassesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddressController {

    private AddressRepository addressRepository;
    private CompleteClassesServiceImp completeClassesServiceImp;

    @Autowired
    public AddressController(AddressRepository addressRepository, CompleteClassesServiceImp completeClassesServiceImp) {
        this.addressRepository = addressRepository;
        this.completeClassesServiceImp = completeClassesServiceImp;
    }

    @PostMapping("/address/new")
    public String newAddress(@ModelAttribute CompletePerson specificPerson, @RequestParam("type") String type) {
        specificPerson.getCompleteAddress().stream().forEach(e -> e.setType(type));
        completeClassesServiceImp.addNewAddressToCompletePerson(specificPerson);
        return "redirect:/person/specific/" + specificPerson.getPersonId();
    }

    @ModelAttribute("addresses")
    public List<Address> addressList() {
        return addressRepository.findAll();
    }

    @ExceptionHandler({InvalidValueException.class})
    public String handleExpectedException(Exception exception, Model model) {
        InvalidValueException invalidValueException = (InvalidValueException) exception;
        model.addAttribute("exception", invalidValueException);
        return "bootstrap/error/generalErrorPage";
    }

    @ExceptionHandler({Exception.class})
    public String handleUnexpectedException(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        exception.printStackTrace();
        return "bootstrap/error/unexpectedError";
    }
}
