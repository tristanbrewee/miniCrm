package com.syntra.tristanbrewee.miniCrm.persistance.services;

import com.syntra.tristanbrewee.miniCrm.model.Community;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompletePerson;
import com.syntra.tristanbrewee.miniCrm.persistance.repositories.CommunityRepository;
import com.syntra.tristanbrewee.miniCrm.utils.Conversions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityServiceImp implements CommunityService{

    private CommunityRepository communityRepository;

    @Autowired
    public CommunityServiceImp(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public List<Community> findAll(){
        return communityRepository.findAll();
    }

    public void save(Community community){
        communityRepository.save(community);
    }

    public void delete(Community community){
        communityRepository.delete(community);
    }

    public Optional<Community> findById(Integer id){
        return communityRepository.findById(id);
    }
}
