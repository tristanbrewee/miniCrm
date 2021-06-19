package com.syntra.tristanbrewee.miniCrm.persistance.repositories;

import static org.junit.Assert.*;

import com.syntra.tristanbrewee.miniCrm.model.Address;
import com.syntra.tristanbrewee.miniCrm.persistance.services.AddressService;
import com.syntra.tristanbrewee.miniCrm.persistance.services.AddressServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
public class TestAddressServiceImp {

    @TestConfiguration
    static class AddressServiceImpTestConfiguration{

        @Bean
        public AddressService addressService(){
            return new AddressServiceImp(addressRepository);
        }

        @MockBean
        private AddressRepository addressRepository;
    }

    @Autowired
    private AddressService addressService;

    Address address1;
    Address address2;
    List<Address> addresses;

    @Before
    public void setUp(){
        address1 = new Address(1, "kakstraat", "1", "A1", "Gent", "9000", "België");
        address2 = new Address(2, "strontStraat", "2", "B2", "Gent", "9000", "België");
        addresses = new ArrayList<>();
        addresses.add(address1);
        addresses.add(address2);
        Mockito.when(addressService.findAll()).thenReturn(addresses);
    }

    @Test
    public void testFindAll(){
        List<Address> found = addressService.findAll();
        assertEquals(addresses.size(), found.size());
        addresses.stream().forEach(e -> assertTrue(found.contains(e)));
    }
}
