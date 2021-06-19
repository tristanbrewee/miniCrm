package com.syntra.tristanbrewee.miniCrm.utils;

import static org.junit.Assert.*;
import static com.syntra.tristanbrewee.miniCrm.utils.Conversions.*;

import com.syntra.tristanbrewee.miniCrm.model.Address;
import com.syntra.tristanbrewee.miniCrm.model.Member;
import com.syntra.tristanbrewee.miniCrm.model.Person;
import com.syntra.tristanbrewee.miniCrm.model.PersonAddress;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompleteAddress;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompleteCommunity;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompletePerson;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TestConversions {

    private CompletePerson completePerson;
    private LocalDate localDate;
    private String localDateString;
    private LocalTime localTime;
    private String localTimeString;

    private int personId;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    private boolean isActive;

    private int addressId1;
    private String bus1;
    private String city1;
    private String country1;
    private String houseNumber1;
    private String street1;
    private String zip1;
    private String email1;
    private String mobile1;
    private String phone1;
    private String type1;

    private int addressId2;
    private String bus2;
    private String city2;
    private String country2;
    private String houseNumber2;
    private String street2;
    private String zip2;
    private String email2;
    private String mobile2;
    private String phone2;
    private String type2;

    private int communityId1;
    private String description1;
    private LocalDate since1;
    private LocalDate until1;

    private int communityId2;
    private String description2;
    private LocalDate since2;
    private LocalDate until2;

    @Before
    public void setUp(){
        localDate = LocalDate.of(2020, 12, 1);
        localDateString = "2020-12-01";
        localTime = LocalTime.of(2, 36);
        localTimeString = "02:36";

        personId = 1;
        birthDate = LocalDate.now();
        firstName = "Bob";
        lastName = "Bobby";
        isActive = true;

        addressId1 = 1;
        bus1 = "A1";
        city1 = "Gent";
        country1 = "België";
        houseNumber1 = "1";
        street1 = "Kakstraat";
        zip1 = "9000";
        email1 = "email1@test.test";
        phone1 = "123456789";
        mobile1 = "0123456789";
        type1 = "work";

        addressId2 = 2;
        bus2 = "B2";
        city2 = "Brussel";
        country2 = "Frankrijk";
        houseNumber2 = "2";
        street2 = "Strontstraat";
        zip2 = "1000";
        email2 = "email2@test.test";
        phone2 = "987654321";
        mobile2 = "9876543210";
        type2 = "other";

        communityId1 = 1;
        description1 = "description1";
        since1 = LocalDate.of(2020, 1, 1);
        until1 = LocalDate.of(2020, 12, 1);

        communityId2 = 2;
        description2 = "description2";
        since2 = LocalDate.of(2021, 1, 1);
        until2 = LocalDate.of(2021, 12, 1);

        completePerson = new CompletePerson();
        completePerson.setPersonId(personId);
        completePerson.setBirthDate(birthDate);
        completePerson.setFirstName(firstName);
        completePerson.setLastName(lastName);
        completePerson.setIsActive(isActive);

        CompleteAddress completeAddress1 = new CompleteAddress();
        completeAddress1.setAddressId(addressId1);
        completeAddress1.setBus(bus1);
        completeAddress1.setCity(city1);
        completeAddress1.setHouseNumber(houseNumber1);
        completeAddress1.setStreet(street1);
        completeAddress1.setZip(zip1);
        completeAddress1.setCountry(country1);
        completeAddress1.setEmail(email1);
        completeAddress1.setMobile(mobile1);
        completeAddress1.setPhone(phone1);
        completeAddress1.setType(type1);

        CompleteAddress completeAddress2 = new CompleteAddress();
        completeAddress2.setAddressId(addressId2);
        completeAddress2.setBus(bus2);
        completeAddress2.setCity(city2);
        completeAddress2.setHouseNumber(houseNumber2);
        completeAddress2.setStreet(street2);
        completeAddress2.setZip(zip2);
        completeAddress2.setCountry(country2);
        completeAddress2.setEmail(email2);
        completeAddress2.setMobile(mobile2);
        completeAddress2.setPhone(phone2);
        completeAddress2.setType(type2);

        List<CompleteAddress> completeAddresses = new ArrayList<>();
        completeAddresses.add(completeAddress1);
        completeAddresses.add(completeAddress2);
        completePerson.setCompleteAddress(completeAddresses);

        CompleteCommunity completeCommunity1 = new CompleteCommunity();
        completeCommunity1.setCommunityId(communityId1);
        completeCommunity1.setDescription(description1);
        completeCommunity1.setSince(since1);
        completeCommunity1.setUntil(until1);

        CompleteCommunity completeCommunity2 = new CompleteCommunity();
        completeCommunity2.setCommunityId(communityId2);
        completeCommunity2.setDescription(description2);
        completeCommunity2.setSince(since2);
        completeCommunity2.setUntil(until2);

        List<CompleteCommunity> completeCommunities = new ArrayList<>();
        completeCommunities.add(completeCommunity1);
        completeCommunities.add(completeCommunity2);
        completePerson.setCompleteCommunities(completeCommunities);
    }

    @Test
    public void testCompletePersonToPerson(){
        Person person = completePersonToPerson(completePerson);
        assertEquals((int) person.getPersonId(), personId);
        assertEquals(person.getFirstName(), firstName);
        assertEquals(person.getLastName(), lastName);
        assertEquals(person.getBirthDate(), birthDate);
        assertEquals(person.getActive(), isActive);

        assertEquals(2, person.getPerson_address().size());

        assertEquals((int) person.getPerson_address().get(0).getAddressId(), addressId1);
        assertEquals(person.getPerson_address().get(0).getStreet(), street1);
        assertEquals(person.getPerson_address().get(0).getHouseNumber(), houseNumber1);
        assertEquals(person.getPerson_address().get(0).getBus(), bus1);
        assertEquals(person.getPerson_address().get(0).getCity(), city1);
        assertEquals(person.getPerson_address().get(0).getZip(), zip1);
        assertEquals(person.getPerson_address().get(0).getCountry(), country1);

        assertEquals((int) person.getPerson_address().get(1).getAddressId(), addressId2);
        assertEquals(person.getPerson_address().get(1).getStreet(), street2);
        assertEquals(person.getPerson_address().get(1).getHouseNumber(), houseNumber2);
        assertEquals(person.getPerson_address().get(1).getBus(), bus2);
        assertEquals(person.getPerson_address().get(1).getCity(), city2);
        assertEquals(person.getPerson_address().get(1).getZip(), zip2);
        assertEquals(person.getPerson_address().get(1).getCountry(), country2);

        assertEquals(2, person.getMember().size());

        assertEquals((int) person.getMember().get(0).getCommunityId(), communityId1);
        assertEquals(person.getMember().get(0).getDescription(), description1);
    }

    @Test
    public void testCompletePersonToMemberList(){
        List<Member> members = completePersonToMemberList(completePerson);

        assertEquals(2, members.size());

        assertEquals((int) members.get(0).getMemberId().getPerson_id(), personId);
        assertEquals((int) members.get(0).getMemberId().getCommunity_id(), communityId1);
        assertEquals(members.get(0).getSince(), since1);
        assertEquals(members.get(0).getUntil(), until1);

        assertEquals((int) members.get(1).getMemberId().getPerson_id(), personId);
        assertEquals((int) members.get(1).getMemberId().getCommunity_id(), communityId2);
        assertEquals(members.get(1).getSince(), since2);
        assertEquals(members.get(1).getUntil(), until2);
    }

    @Test
    public void testCompletePersonToPersonAddressList(){
        List<PersonAddress> personAddresses = completePersonToPersonAddressList(completePerson);

        assertEquals(2, personAddresses.size());

        assertEquals((int) personAddresses.get(0).getPersonAddressId().getPerson_id(), personId);
        assertEquals((int) personAddresses.get(0).getPersonAddressId().getAddress_id(), addressId1);
        assertEquals(personAddresses.get(0).getEmail(), email1);
        assertEquals(personAddresses.get(0).getPhone(), phone1);
        assertEquals(personAddresses.get(0).getMobile(), mobile1);
        assertEquals(personAddresses.get(0).getType(), type1);

        assertEquals((int) personAddresses.get(1).getPersonAddressId().getPerson_id(), personId);
        assertEquals((int) personAddresses.get(1).getPersonAddressId().getAddress_id(), addressId2);
        assertEquals(personAddresses.get(1).getEmail(), email2);
        assertEquals(personAddresses.get(1).getPhone(), phone2);
        assertEquals(personAddresses.get(1).getMobile(), mobile2);
        assertEquals(personAddresses.get(1).getType(), type2);
    }

    @Test
    public void testCompletePersonToAddressList(){
        List<Address> addresses = completePersonToAddressList(completePerson);

        assertEquals(2, addresses.size());

        assertEquals((int) addresses.get(0).getAddressId(), addressId1);
        assertEquals(addresses.get(0).getStreet(), street1);
        assertEquals(addresses.get(0).getHouseNumber(), houseNumber1);
        assertEquals(addresses.get(0).getBus(), bus1);
        assertEquals(addresses.get(0).getCity(), city1);
        assertEquals(addresses.get(0).getZip(), zip1);
        assertEquals(addresses.get(0).getCountry(), country1);

        assertEquals((int) addresses.get(1).getAddressId(), addressId2);
        assertEquals(addresses.get(1).getStreet(), street2);
        assertEquals(addresses.get(1).getHouseNumber(), houseNumber2);
        assertEquals(addresses.get(1).getBus(), bus2);
        assertEquals(addresses.get(1).getCity(), city2);
        assertEquals(addresses.get(1).getZip(), zip2);
        assertEquals(addresses.get(1).getCountry(), country2);
    }

    @Test
    public void testLocalTimeToString(){
        assertEquals(localTimeToString(localTime), localTimeString);
    }

    @Test
    public void testStringToLocalTime(){
        assertEquals(stringToLocalTime(localTimeString), localTime);
    }

    @Test
    public void testLocalDateToString(){
        assertEquals(localDateToString(localDate), localDateString);
    }

    @Test
    public void testStringToLocalDate(){
        assertEquals(stringToLocalDate(localDateString), localDate);
    }
}
