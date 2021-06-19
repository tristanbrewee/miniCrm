package com.syntra.tristanbrewee.miniCrm.persistance.services;

import com.syntra.tristanbrewee.miniCrm.model.*;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompleteAddress;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompleteCommunity;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompleteMember;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompletePerson;
import com.syntra.tristanbrewee.miniCrm.model.idclasses.MemberId;
import com.syntra.tristanbrewee.miniCrm.model.idclasses.PersonAddressId;
import com.syntra.tristanbrewee.miniCrm.utils.ComparatorCompleteMember;
import com.syntra.tristanbrewee.miniCrm.utils.Conversions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompleteClassesServiceImp implements CompleteClassesService {

    private PersonAddressServiceImp personAddressServiceImp;
    private AddressServiceImp addressServiceImp;
    private PersonServiceImp personServiceImp;
    private MemberServiceImp memberServiceImp;
    private CommunityServiceImp communityServiceImp;

    @Autowired
    public CompleteClassesServiceImp(PersonAddressServiceImp personAddressServiceImp,
                                     AddressServiceImp addressServiceImp, PersonServiceImp personServiceImp,
                                     MemberServiceImp memberServiceImp, CommunityServiceImp communityServiceImp) {
        this.personAddressServiceImp = personAddressServiceImp;
        this.addressServiceImp = addressServiceImp;
        this.personServiceImp = personServiceImp;
        this.memberServiceImp = memberServiceImp;
        this.communityServiceImp = communityServiceImp;
    }

    public CompletePerson getByPersonId(Integer personId) {
        return getAllByStreams()
                .stream()
                .filter(e -> e.getPersonId() == personId)
                .collect(Collectors.toList())
                .get(0);
    }

    public List<CompletePerson> getAllCompletePersonsByActive() {
        return getAllByStreams()
                .stream()
                .filter(e -> e.getIsActive())
                .collect(Collectors.toList());
    }

    public List<CompletePerson> getAllByStreams() {
        List<CompletePerson> completePersonList = new ArrayList<>();
        personServiceImp.findAll()
                .stream()
                .forEach(e -> {
                    CompletePerson completePerson = new CompletePerson();
                    completePerson.setPersonId(e.getPersonId());
                    completePerson.setBirthDate(e.getBirthDate());
                    completePerson.setFirstName(e.getFirstName());
                    completePerson.setLastName(e.getLastName());
                    completePerson.setIsActive(e.getActive());
                    completePerson.setCompleteAddress(new ArrayList<>());
                    completePerson.setCompleteCommunities(new ArrayList<>());
                    completePersonList.add(completePerson);
                });
        personAddressServiceImp.findAll()
                .stream()
                .forEach(personAddress -> completePersonList.stream()
                        .filter(completePerson -> personAddress.getPersonAddressId().getPerson_id() == completePerson.getPersonId())
                        .forEach(d -> {
                            CompleteAddress completeAddress = new CompleteAddress();
                            completeAddress.setAddressId(personAddress.getPersonAddressId().getAddress_id());
                            completeAddress.setEmail(personAddress.getEmail());
                            completeAddress.setMobile(personAddress.getMobile());
                            completeAddress.setPhone(personAddress.getPhone());
                            completeAddress.setType(personAddress.getType());
                            d.getCompleteAddress().add(completeAddress);
                        })
                );
        addressServiceImp.findAll()
                .stream()
                .forEach(address -> completePersonList.stream()
                        .forEach(person -> person.getCompleteAddress()
                                .stream()
                                .filter(completeAddress -> completeAddress.getAddressId() == address.getAddressId())
                                .forEach(completeAddress -> {
                                    completeAddress.setBus(address.getBus());
                                    completeAddress.setCity(address.getCity());
                                    completeAddress.setCountry(address.getCountry());
                                    completeAddress.setHouseNumber(address.getHouseNumber());
                                    completeAddress.setStreet(address.getStreet());
                                    completeAddress.setZip(address.getZip());
                                })
                        )
                );
        memberServiceImp.findAll()
                .stream()
                .forEach(member -> completePersonList.stream()
                        .filter(completePerson -> completePerson.getPersonId() == member.getMemberId().getPerson_id())
                        .forEach(completePerson -> {
                            CompleteCommunity completeCommunity = new CompleteCommunity();
                            completeCommunity.setCommunityId(member.getMemberId().getCommunity_id());
                            completeCommunity.setSince(member.getSince());
                            completeCommunity.setUntil(member.getUntil());
                            completePerson.getCompleteCommunities().add(completeCommunity);
                        })
                );
        communityServiceImp.findAll()
                .stream()
                .forEach(community -> completePersonList.stream()
                        .forEach(completePerson -> completePerson.getCompleteCommunities()
                                .stream()
                                .filter(completeCommunity -> completeCommunity.getCommunityId() == community.getCommunityId())
                                .forEach(completeCommunity -> completeCommunity.setDescription(community.getDescription()))
                        )
                );
        return completePersonList;
    }

    public void saveCompletePersonAll(CompletePerson completePerson) {
        Person person = Conversions.completePersonToPerson(completePerson);
        personServiceImp.checkIfPersonExists(person);
        List<Address> addresses = Conversions.completePersonToAddressList(completePerson);
        addresses.stream().forEach(addressServiceImp::checkForAddressErrors);
        saveCompletePersonPersonalInfo(completePerson);
        for (int i = 0; i < completePerson.getCompleteAddress().size(); i++) {
            personAddressServiceImp.checkForPersonAddressErrors(Conversions.completePersonToPersonAddressList(completePerson).get(i));
            updateAddressOfCompletePerson(completePerson, i);
        }
    }

    public void saveCompletePersonPersonalInfo(CompletePerson completePerson) {
        Person person = Conversions.completePersonToPerson(completePerson);
        personServiceImp.checkForPersonErrors(person);
        List<PersonAddress> personAddresses = personAddressServiceImp.findByPersonId(completePerson.getPersonId());
        personServiceImp.savePerson(person);
        personAddresses.stream()
                .forEach(e -> personAddressServiceImp.savePersonAddress(e));
    }

    public void deleteCompletePerson(CompletePerson completePerson) {
        completePerson.setIsActive(false);
        personServiceImp.savePerson(Conversions.completePersonToPerson(completePerson));
    }

    public void deleteAddressOfCompletePerson(CompletePerson completePerson, Integer index) {
        List<PersonAddress> personAddresses = Conversions.completePersonToPersonAddressList(completePerson);
        if (!addressIsLinkedToOtherPerson(personAddresses.get(index))) {
            addressServiceImp.deleteById(personAddresses.get(index).getPersonAddressId().getAddress_id());
        }
        personAddressServiceImp.deleteByPersonIdAndAddressId(completePerson.getPersonId(), personAddresses.get(index).getPersonAddressId().getAddress_id());
    }

    public void updateAddressOfCompletePerson(CompletePerson completePerson, Integer index) {
        List<PersonAddress> personAddresses = Conversions.completePersonToPersonAddressList(completePerson);
        if (addressIsLinkedToOtherPerson(personAddresses.get(index))) {
            Address newAddress = new Address();
            newAddress.setStreet(completePerson.getCompleteAddress().get(index).getStreet());
            newAddress.setHouseNumber(completePerson.getCompleteAddress().get(index).getHouseNumber());
            newAddress.setBus(completePerson.getCompleteAddress().get(index).getBus());
            newAddress.setCity(completePerson.getCompleteAddress().get(index).getCity());
            newAddress.setZip(completePerson.getCompleteAddress().get(index).getZip());
            newAddress.setCountry(completePerson.getCompleteAddress().get(index).getCountry());
            addressServiceImp.saveAddress(newAddress);

            Integer oldAddressId = personAddresses.get(index).getPersonAddressId().getAddress_id();

            personAddresses.get(index).getPersonAddressId().setAddress_id(
                    addressServiceImp.findByStreetAndHouseNumberAndBusAndCityAndZipAndCountry(
                            newAddress.getStreet(), newAddress.getHouseNumber(), newAddress.getBus(), newAddress.getCity(), newAddress.getZip(), newAddress.getCountry()
                    ).get(0).getAddressId());
            personAddressServiceImp.deleteByPersonIdAndAddressId(completePerson.getPersonId(), oldAddressId);
            personAddressServiceImp.checkForPersonAddressErrors(personAddresses.get(index));
        } else {
            Address newAddress = Conversions.completePersonToAddressList(completePerson).get(index);
            addressServiceImp.normalizeAddress(newAddress);
            addressServiceImp.checkForAddressErrors(newAddress);
            personAddressServiceImp.checkForPersonAddressErrors(personAddresses.get(index));
            addressServiceImp.saveAddress(newAddress);
        }
        personAddressServiceImp.savePersonAddress(personAddresses.get(index));
    }

    public void addNewAddressToCompletePerson(CompletePerson completePerson) {
        Address newAddress = Conversions.completePersonToAddressList(completePerson).get(0);
        addressServiceImp.normalizeAddress(newAddress);
        if (!addressAlreadyExists(newAddress)) {
            addressServiceImp.checkForAddressErrors(newAddress);
            addressServiceImp.saveAddress(newAddress);
        }
        PersonAddress personAddress = Conversions.completePersonToPersonAddressList(completePerson).get(0);
        Integer addressId = addressServiceImp.findByStreetAndHouseNumberAndBusAndCityAndZipAndCountry(
                newAddress.getStreet(), newAddress.getHouseNumber(), newAddress.getBus(), newAddress.getCity(), newAddress.getZip(), newAddress.getCountry()
        ).get(0).getAddressId();
        personAddress.setPersonAddressId(new PersonAddressId(completePerson.getPersonId(), addressId));
        personAddressServiceImp.checkForPersonAddressErrors(personAddress);
        personAddressServiceImp.savePersonAddress(personAddress);
    }

    public List<Integer> getAllCommunityIds(CompletePerson completePerson) {
        if (completePerson.getCompleteAddress() == null)
            return new ArrayList<>();
        return completePerson.getCompleteCommunities()
                .stream()
                .filter(e -> e.getUntil() == null)
                .mapToInt(CompleteCommunity::getCommunityId)
                .boxed()
                .collect(Collectors.toList());
    }

    public void addCommunityToCompletePerson(CompletePerson completePerson, Integer communityId) {
        Member newMember = new Member(new MemberId(communityId, completePerson.getPersonId()), LocalDate.now(), null);
        memberServiceImp.saveMember(newMember);
    }

    public List<CompleteMember> getCompleteMembersByEvent(Integer communityId) {
        List<CompleteMember> completeMembers = new ArrayList<>();
        memberServiceImp.findAll()
                .stream()
                .filter(member -> member.getMemberId().getCommunity_id() == communityId)
                .forEach(member -> {
                    CompleteMember completeMember = new CompleteMember();
                    completeMember.setPersonId(member.getMemberId().getPerson_id());
                    completeMember.setSince(member.getSince());
                    completeMember.setUntil(member.getUntil());
                    completeMembers.add(completeMember);
                });
        personServiceImp.findAll()
                .stream()
                .forEach(person -> completeMembers.stream()
                        .filter(completeMember -> completeMember.getPersonId() == person.getPersonId())
                        .forEach(completeMember -> {
                            completeMember.setFirstName(person.getFirstName());
                            completeMember.setLastName(person.getLastName());
                        })
                );
        return completeMembers.stream()
                .sorted(new ComparatorCompleteMember()
                        .thenComparing(CompleteMember::getLastName)
                        .thenComparing(CompleteMember::getFirstName)
                )
                .collect(Collectors.toList());
    }

    private boolean addressAlreadyExists(Address address) {
        if (addressServiceImp.findByStreetAndHouseNumberAndBusAndCityAndZipAndCountry(
                address.getStreet(), address.getHouseNumber(), address.getBus(), address.getCity(), address.getZip(), address.getCountry()
        ).isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean addressIsLinkedToOtherPerson(PersonAddress personAddressToCheck) {
        for (PersonAddress personAddress : personAddressServiceImp.findAll()) {
            if (personAddress.getPersonAddressId().getAddress_id() == personAddressToCheck.getPersonAddressId().getAddress_id() &&
                    personAddress.getPersonAddressId().getPerson_id() != personAddressToCheck.getPersonAddressId().getPerson_id()) {
                return true;
            }
        }
        return false;
    }
}
