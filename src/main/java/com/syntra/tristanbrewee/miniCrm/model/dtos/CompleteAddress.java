package com.syntra.tristanbrewee.miniCrm.model.dtos;

public class CompleteAddress {

    private Integer addressId;
    private String bus;
    private String city;
    private String country;
    private String houseNumber;
    private String street;
    private String zip;

    private String email;
    private String mobile;
    private String phone;
    private String type;

    public CompleteAddress(Integer addressId, String bus, String city, String country, String houseNumber, String street, String zip, String email, String mobile, String phone, String type) {
        this.addressId = addressId;
        this.bus = bus;
        this.city = city;
        this.country = country;
        this.houseNumber = houseNumber;
        this.street = street;
        this.zip = zip;
        this.email = email;
        this.mobile = mobile;
        this.phone = phone;
        this.type = type;
    }

    public CompleteAddress() {
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
