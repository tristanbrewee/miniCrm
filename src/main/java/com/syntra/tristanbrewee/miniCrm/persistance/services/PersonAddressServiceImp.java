package com.syntra.tristanbrewee.miniCrm.persistance.services;

import com.syntra.tristanbrewee.miniCrm.exceptions.InvalidValueException;
import com.syntra.tristanbrewee.miniCrm.model.PersonAddress;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompletePerson;
import com.syntra.tristanbrewee.miniCrm.model.idclasses.PersonAddressId;
import com.syntra.tristanbrewee.miniCrm.persistance.repositories.PersonAddressRepository;
import com.syntra.tristanbrewee.miniCrm.utils.Conversions;
import com.syntra.tristanbrewee.miniCrm.utils.PersonAddressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonAddressServiceImp implements PersonAddressService {

    @PersistenceContext
    private EntityManager entityManager;

    private PersonAddressRepository personAddressRepository;

    @Autowired
    public PersonAddressServiceImp(PersonAddressRepository personAddressRepository) {
        this.personAddressRepository = personAddressRepository;
    }

    public List<PersonAddress> findByPersonId(Integer personId){
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM person_address WHERE person_id = " + personId
        );
        List<Object[]> x = query.getResultList();
        List<PersonAddress> personAddresses = new ArrayList<>();
        for (Object[] objectArray: x) {
            PersonAddress personAddress = objectArrayToPersonAddress(objectArray);
            personAddresses.add(personAddress);
        }
        return personAddresses;
    }

    public void saveCompletePerson(CompletePerson completePerson) {
        Conversions.completePersonToPersonAddressList(completePerson)
                .stream()
                .forEach(e ->
                        personAddressRepository.save(e)
                );
    }

    public void savePersonAddress(PersonAddress personAddress){
        personAddressRepository.save(personAddress);
    }

    public List<PersonAddress> findAll(){
        return personAddressRepository.findAll();
    }

    @Transactional
    public void deleteByPersonIdAndAddressId(Integer personId, Integer addressId){
        String queryString = "DELETE FROM person_address WHERE person_address.person_id = "+ personId +" AND person_address.address_id = " + addressId;
        Query query = entityManager.createNativeQuery(
                queryString
        );
        entityManager.joinTransaction();
        query.executeUpdate();
    }

    @Transactional
    public void updateAddressIdByPersonIdAndAddressId(Integer personId, Integer addressIdOld, Integer addressIdNew){
        Query query = entityManager.createNativeQuery("UPDATE person_address SET address_id = " + addressIdNew +
                " WHERE address_id = " + addressIdOld + " AND person_id = " + personId);
        entityManager.joinTransaction();
        query.executeUpdate();
    }

    public void checkForPersonAddressErrors(PersonAddress personAddress){
        List<String> errors = PersonAddressUtils.checkPersonAddressForInvalidInput(personAddress);
        if (!errors.isEmpty())
            throw new InvalidValueException(errors);
    }

    private PersonAddress objectArrayToPersonAddress(Object[] objectArray){
        Integer personId = (Integer) objectArray[0];
        Integer addressId = (Integer) objectArray[1];
        PersonAddressId personAddressId = new PersonAddressId(personId, addressId);
        String email = (String) objectArray[2];
        String phone = (String) objectArray[3];
        String mobile = (String) objectArray[4];
        String type = (String) objectArray[5];
        return new PersonAddress(personAddressId, email, phone, mobile, type);
    }
}
