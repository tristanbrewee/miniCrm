package com.syntra.tristanbrewee.miniCrm.persistance.repositories;

import com.syntra.tristanbrewee.miniCrm.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
