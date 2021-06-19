package com.syntra.tristanbrewee.miniCrm.model.idclasses;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonAddressId implements Serializable {

    @Column
    private Integer person_id;
    @Column
    private Integer address_id;

    //Constructors
    public PersonAddressId(Integer person_id, Integer address_id) {
        this.person_id = person_id;
        this.address_id = address_id;
    }

    public PersonAddressId() {
    }

    //Getters and Setters
    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer personId) {
        this.person_id = personId;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer addressId) {
        this.address_id = addressId;
    }

    //Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonAddressId that = (PersonAddressId) o;
        return Objects.equals(person_id, that.person_id) && Objects.equals(address_id, that.address_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person_id, address_id);
    }
}
