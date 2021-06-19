package com.syntra.tristanbrewee.miniCrm.persistance.services;

import com.syntra.tristanbrewee.miniCrm.exceptions.InvalidValueException;
import com.syntra.tristanbrewee.miniCrm.model.Address;
import com.syntra.tristanbrewee.miniCrm.persistance.repositories.AddressRepository;
import com.syntra.tristanbrewee.miniCrm.utils.AddressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService{

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImp(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void saveAddress(Address address){
        addressRepository.save(address);
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public List<Address> findByStreetAndHouseNumberAndBusAndCityAndZipAndCountry(String street, String houseNumber, String bus,
                                                                                          String city, String zip, String country){
        return addressRepository.findByStreetAndHouseNumberAndBusAndCityAndZipAndCountry(street, houseNumber, bus, city, zip, country);
    }

    public void deleteById(Integer id){
        addressRepository.deleteById(id);
    }

    public void normalizeAddress(Address address){
        if (address.getStreet() != null && address.getStreet().isEmpty())
            address.setStreet(null);
        if (address.getHouseNumber() != null && address.getHouseNumber().isEmpty())
            address.setHouseNumber(null);
        if (address.getBus() != null && address.getBus().isEmpty())
            address.setBus(null);
        if (address.getCity() != null && address.getCity().isEmpty())
            address.setCity(null);
        if (address.getZip() != null && address.getZip().isEmpty())
            address.setZip(null);
        if (address.getCountry() != null && address.getCountry().isEmpty())
            address.setCountry(null);
    }

    public void checkForAddressErrors(Address newAddress){
        List<String> errors = AddressUtils.checkIfNoFieldIsNullExceptBusIfAnyFieldIsNotNull(newAddress);
        if (!errors.isEmpty())
            throw new InvalidValueException(errors);
        errors = AddressUtils.checkIfAllFieldsHaveCorrectInputIfAnyFieldIsNotNull(newAddress);
        if (!errors.isEmpty())
            throw new InvalidValueException(errors);
    }
}
