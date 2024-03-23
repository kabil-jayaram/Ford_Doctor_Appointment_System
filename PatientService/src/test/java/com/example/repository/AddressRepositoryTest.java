package com.example.repository;

import com.example.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class AddressRepositoryTest {
    @MockBean
    private AddressRepository addressRepository;

    @Test
    void addAddress() {
        // Given
        Address address = Address.builder().id(1).dno(98).street("Anna Nagar").build();
        // When
        when(addressRepository.save(address)).thenReturn(address);
        // Then
        Address savedAddress = addressRepository.save(address);
        assertThat(savedAddress).isNotNull();
        assertThat(savedAddress).isEqualTo(address);
        System.out.println(savedAddress);
    }

    @Test
    void updateAddress() {
        // Given
        int id = 1;
        Address originalAddress = Address.builder().id(1).dno(98).street("Anna Nagar").build();
        Address addressToUpdate = Address.builder().id(1).dno(12).street("Anna Nagar").build();
        // When
        when(addressRepository.save(any(Address.class))).thenReturn(addressToUpdate);
        when(addressRepository.findById(id)).thenReturn(Optional.of(originalAddress));
        // Then
        Address retrievedAddress = addressRepository.findById(id).get();
        retrievedAddress.setId(addressToUpdate.getId());
        retrievedAddress.setDno(addressToUpdate.getDno());
        retrievedAddress.setStreet(addressToUpdate.getStreet());
        Address updatedAddress = addressRepository.save(retrievedAddress);
        assertThat(updatedAddress).isNotNull();
        assertThat(updatedAddress).isEqualTo(addressToUpdate);
        System.out.println(updatedAddress);
    }

    @Test
    void deleteAddress() {
        // Given
        int id = 1;
        Address address = Address.builder().id(1).dno(98).street("Anna Nagar").build();
        // When
        when(addressRepository.findById(id)).thenReturn(Optional.of(address));
        // Then
        addressRepository.deleteById(id);
        verify(addressRepository, times(1)).deleteById(id);
    }
}