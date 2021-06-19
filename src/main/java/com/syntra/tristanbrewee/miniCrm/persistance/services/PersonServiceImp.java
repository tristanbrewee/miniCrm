package com.syntra.tristanbrewee.miniCrm.persistance.services;

import com.syntra.tristanbrewee.miniCrm.exceptions.InvalidValueException;
import com.syntra.tristanbrewee.miniCrm.exceptions.PersonAlreadyExistsException;
import com.syntra.tristanbrewee.miniCrm.model.Person;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompletePerson;
import com.syntra.tristanbrewee.miniCrm.persistance.repositories.PersonRepository;
import com.syntra.tristanbrewee.miniCrm.utils.Conversions;
import com.syntra.tristanbrewee.miniCrm.utils.PersonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImp {

    private EntityManager entityManager;

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void saveCompletePerson(CompletePerson completePerson){
        personRepository.save(Conversions.completePersonToPerson(completePerson));
    }

    public void savePerson(Person person){
        personRepository.save(person);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public List<Person> findAllByInactive(){
        return personRepository.findAll()
                .stream()
                .filter(e -> !e.getActive())
                .sorted(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName).thenComparing(Person::getBirthDate))
                .collect(Collectors.toList());
    }

    public List<Person> findAllByActive(){
        return personRepository.findAll()
                .stream()
                .filter(Person::getActive)
                .sorted(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName).thenComparing(Person::getBirthDate))
                .collect(Collectors.toList());
    }

    public Optional<Person> findById(Integer id){
        return personRepository.findById(id);
    }

    public void activatePersonById(Integer id){
        Person person = personRepository.findById(id).get();
        person.setActive(true);
        personRepository.save(person);
    }

    public void delete(Integer id){
        Person person = personRepository.findById(id).get();
        person.setActive(false);
        personRepository.save(person);
    }

    public void createNewPerson(Person person){
        normalizePerson(person);
        checkForPersonErrors(person);
        personRepository.save(person);
    }

    public boolean checkIfPersonExists(Person person){
        List<Person> isHere = personRepository.findAll()
                .stream()
                .filter(e -> e.getFirstName().equals(person.getFirstName()))
                .filter(e -> e.getLastName().equals(person.getLastName()))
                .filter(e -> e.getBirthDate().equals(person.getBirthDate()))
                .collect(Collectors.toList());
        return !isHere.isEmpty();
    }

    public Person findByIdAndCommunityId(Integer personId, Integer communityId){
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM person " +
                        "WHERE person_id IN " +
                        "(SELECT member.person_id FROM member " +
                        "WHERE community_id = " + communityId + " " +
                        "AND member.person_id = " + personId +")"
        );
        List<Object[]> x = query.getResultList();
        System.out.println("----------");
        System.out.println("----------");
        System.out.println("----------");
        System.out.println("insisde findByIdAndCommunityId");
        System.out.println(x.get(0)[0].toString());
        System.out.println("----------");
        System.out.println("----------");
        System.out.println("----------");
        if (x.isEmpty())
            return null;
        return objectArrayToPerson(x.get(0));
    }

    private Person objectArrayToPerson(Object[] objects){
        Integer personId = (Integer) (objects[0]);
        String lastName = (String) objects[1];
        String firstName = (String) objects[2];
        LocalDate birthDate;
        if (objects[3] == null)
            birthDate = null;
        else {
            Date date = (Date) objects[3];
            birthDate = date.toLocalDate();
        }
        boolean isActive = (Boolean) objects[4];
        return new Person(personId, lastName, firstName, birthDate, isActive);
    }

    public void normalizePerson(Person person){
        if (person.getFirstName().isEmpty())
            person.setFirstName(null);
        if (person.getLastName().isEmpty())
            person.setLastName(null);
    }

    public void checkForPersonErrors(Person person){
        if (checkIfPersonExists(person))
            throw new PersonAlreadyExistsException(null, person);
        List<String> errors = PersonUtils.checkForNullValues(person);
        if (!errors.isEmpty())
            throw new InvalidValueException(errors);
        errors = PersonUtils.checkForInvalidInput(person);
        if (!errors.isEmpty())
            throw new InvalidValueException(errors);
    }
}
