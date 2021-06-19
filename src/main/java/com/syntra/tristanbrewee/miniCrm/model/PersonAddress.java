package com.syntra.tristanbrewee.miniCrm.model;

import com.syntra.tristanbrewee.miniCrm.model.idclasses.PersonAddressId;

import javax.persistence.*;

@Entity
@Table(name = "person_address")
public class PersonAddress {

    @EmbeddedId
    private PersonAddressId personAddressId;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "type")
    private String type;

    //Constructors
    public PersonAddress(PersonAddressId personAddressId, String email, String phone, String mobile, String type) {
        this.personAddressId = personAddressId;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.type = type;
    }

    public PersonAddress(PersonAddress personAddress){
        this(personAddress.personAddressId, personAddress.getEmail(), personAddress.getPhone(), personAddress.getMobile(), personAddress.getType());
    }

    public PersonAddress() {
    }

    //Getters and Setters
    public PersonAddressId getPersonAddressId() {
        return personAddressId;
    }

    public void setPersonAddressId(PersonAddressId personAddressId) {
        this.personAddressId = personAddressId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
