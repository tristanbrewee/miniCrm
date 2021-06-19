package com.syntra.tristanbrewee.miniCrm.persistance.repositories;

import com.syntra.tristanbrewee.miniCrm.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
}
