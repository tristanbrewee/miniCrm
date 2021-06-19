package com.syntra.tristanbrewee.miniCrm.utils;

import com.syntra.tristanbrewee.miniCrm.model.*;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompletePerson;
import com.syntra.tristanbrewee.miniCrm.model.idclasses.MemberId;
import com.syntra.tristanbrewee.miniCrm.model.idclasses.PersonAddressId;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Conversions {

    public static Person completePersonToPerson(CompletePerson completePerson) {
        Person person = new Person();
        person.setPersonId(completePerson.getPersonId());
        person.setFirstName(completePerson.getFirstName());
        person.setLastName(completePerson.getLastName());
        person.setBirthDate(completePerson.getBirthDate());
        person.setActive(completePerson.getIsActive());

        ArrayList<Address> addresses = new ArrayList<>();
        completePerson.getCompleteAddress()
                .stream()
                .forEach(e -> {
                    addresses.add(new Address(
                            e.getAddressId(),
                            e.getStreet(),
                            e.getHouseNumber(),
                            e.getBus(),
                            e.getCity(),
                            e.getZip(),
                            e.getCountry()
                    ));
                });
        person.setPerson_address(addresses);

        ArrayList<Community> communities = new ArrayList<>();
        completePerson.getCompleteCommunities()
                .stream()
                .forEach(e ->
                        communities.add(new Community(
                                e.getCommunityId(),
                                e.getDescription()
                        ))
                );
        person.setMember(communities);
        return person;
    }

    public static List<Member> completePersonToMemberList(CompletePerson completePerson) {
        ArrayList<Member> members = new ArrayList<>();
        completePerson.getCompleteCommunities()
                .stream()
                .forEach(e -> {
                            MemberId memberId = new MemberId(
                                    e.getCommunityId(),
                                    completePerson.getPersonId()
                            );
                            members.add(new Member(
                                            memberId,
                                            e.getSince(),
                                            e.getUntil()
                                    )
                            );
                        }
                );
        return members;
    }

    public static List<PersonAddress> completePersonToPersonAddressList(CompletePerson completePerson) {
        ArrayList<PersonAddress> personAddresses = new ArrayList<>();
        completePerson.getCompleteAddress()
                .stream()
                .forEach(e -> {
                            PersonAddressId personAddressId = new PersonAddressId(
                                    completePerson.getPersonId(),
                                    e.getAddressId()
                            );
                            personAddresses.add(new PersonAddress(
                                            personAddressId,
                                            e.getEmail(),
                                            e.getPhone(),
                                            e.getMobile(),
                                            e.getType()
                                    )
                            );
                        }
                );
        return personAddresses;
    }

    public static List<Address> completePersonToAddressList(CompletePerson completePerson){
        List<Address> addresses = new ArrayList<>();
        completePerson.getCompleteAddress()
                .stream()
                .forEach(e ->
                        addresses.add(new Address(
                                e.getAddressId(),
                                e.getStreet(),
                                e.getHouseNumber(),
                                e.getBus(),
                                e.getCity(),
                                e.getZip(),
                                e.getCountry()
                        ))
                        );
        return addresses;
    }

    public static String localTimeToString(LocalTime time){
        String hours = String.valueOf(time.getHour());
        if (hours.length() < 2)
            hours = "0" + hours;
        String minutes = String.valueOf(time.getMinute());
        if (minutes.length() < 2)
            minutes = "0" + minutes;
        return hours + ":" + minutes;
    }

    public static LocalTime stringToLocalTime(String string){
        int hours = Integer.parseInt(string.substring(0, 2));
        int minutes = Integer.parseInt(string.substring(3));
        return LocalTime.of(hours, minutes);
    }

    public static String localDateToString(LocalDate date){
        if (date == null)
            return null;
        String year = String.valueOf(date.getYear());
        String month = String.valueOf(date.getMonthValue());
        if (month.length() == 1)
            month = "0" + month;
        String day = String.valueOf(date.getDayOfMonth());
        if (day.length() == 1)
            day = "0" + day;
        String completeDate = year + "-" + month + "-" + day;
        return completeDate;
    }

    public static LocalDate stringToLocalDate(String string){
        int year = Integer.parseInt(string.substring(0, 4));
        int month = Integer.parseInt(string.substring(5, 7));
        int day = Integer.parseInt(string.substring(8));
        return LocalDate.of(year, month, day);
    }

    public static String dateAsStringNormalize(String dateAsString){
        return dateAsString.substring(8) + "/" + dateAsString.substring(5, 7) + "/" + dateAsString.substring(0, 4);
    }
}
