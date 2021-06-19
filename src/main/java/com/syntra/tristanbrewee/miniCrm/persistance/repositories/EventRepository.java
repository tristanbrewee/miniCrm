package com.syntra.tristanbrewee.miniCrm.persistance.repositories;

import com.syntra.tristanbrewee.miniCrm.model.Community;
import com.syntra.tristanbrewee.miniCrm.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}
