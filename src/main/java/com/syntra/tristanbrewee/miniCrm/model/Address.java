package com.syntra.tristanbrewee.miniCrm.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @ManyToMany(mappedBy = "person_address")
    private List<Person> person_address;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "bus")
    private String bus;

    @Column(name = "city")
    private String city;

    @Column(name = "zip")
    private String zip;

    @Column(name = "country")
    private String country;

    //Constructors
    public Address(Integer addressId, String street, String houseNumber, String bus, String city, String zip, String country) {
        this.addressId = addressId;
        this.street = street;
        this.houseNumber = houseNumber;
        this.bus = bus;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    public Address (Address address){
        this(address.getAddressId(), address.getStreet(), address.getHouseNumber(), address.getBus(), address.getCity(), address.getZip(), address.getCountry());
    }

    public Address() {
    }

    //Getters and Setters
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Person> getPerson_address() {
        return person_address;
    }

    public void setPerson_address(List<Person> person_address) {
        this.person_address = person_address;
    }
}
