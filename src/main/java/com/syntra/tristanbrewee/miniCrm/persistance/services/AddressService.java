package com.syntra.tristanbrewee.miniCrm.persistance.services;

import com.syntra.tristanbrewee.miniCrm.model.Address;

import java.util.List;

public interface AddressService {

    public void saveAddress(Address address);
    public List<Address> findAll();
    public List<Address> findByStreetAndHouseNumberAndBusAndCityAndZipAndCountry(String street, String houseNumber, String bus,
                                                                                 String city, String zip, String country);
    public void deleteById(Integer id);
    public void normalizeAddress(Address address);
    public void checkForAddressErrors(Address newAddress);
}
