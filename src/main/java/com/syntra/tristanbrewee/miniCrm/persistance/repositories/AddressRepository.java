package com.syntra.tristanbrewee.miniCrm.persistance.repositories;

import com.syntra.tristanbrewee.miniCrm.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByStreetAndHouseNumberAndBusAndCityAndZipAndCountry(String street, String houseNumber, String bus,
                                                                                    String city, String zip, String country);

}
