package com.syntra.tristanbrewee.miniCrm.persistance.repositories;

import com.syntra.tristanbrewee.miniCrm.model.PersonAddress;
import com.syntra.tristanbrewee.miniCrm.model.idclasses.PersonAddressId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonAddressRepository extends JpaRepository<PersonAddress, PersonAddressId> {

}
