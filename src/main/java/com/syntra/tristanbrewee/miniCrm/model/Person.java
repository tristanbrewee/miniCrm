package com.syntra.tristanbrewee.miniCrm.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;

    @ManyToMany
    @JoinTable(
            name = "person_address",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    )
    private List<Address> person_address;

    @ManyToMany
    @JoinTable(
            name = "member",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "community_id", referencedColumnName = "community_id")
    )
    private List<Community> member;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "is_active")
    private Boolean isActive;

    //Constructors
    public Person(int personId, String lastName, String firstName, LocalDate birthDate, boolean isActive) {
        this.personId = personId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.isActive = isActive;
    }

    public Person(Person person){
        this(person.getPersonId(), person.getLastName(), person.getFirstName(), person.getBirthDate(), person.isActive);
    }

    public Person() {
    }

    //Overrides
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (this == null)
            return false;
        if (o.getClass() != this.getClass())
            return false;
        Person person = (Person) o;
        return (person.getPersonId().equals(this.getPersonId())
        && person.getLastName().equals(this.getLastName())
        && person.getFirstName().equals(this.getFirstName())
        && person.getActive() == this.getActive()
        && 0 == person.getBirthDate().compareTo(this.getBirthDate()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, person_address, member, lastName, firstName, birthDate, isActive);
    }

    //Getters and Setters
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Address> getPerson_address() {
        return person_address;
    }

    public void setPerson_address(List<Address> person_address) {
        this.person_address = person_address;
    }

    public List<Community> getMember() {
        return member;
    }

    public void setMember(List<Community> member) {
        this.member = member;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
