package com.ecommerce.project.service.Address;

import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.Address.AddressDTO;
import com.ecommerce.project.payload.Address.AddressResponse;
import jakarta.validation.Valid;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);

    AddressResponse getAllAddresses();

    AddressDTO getAddressById(Long addressId);

    AddressResponse getUserAddresses(User user);

    AddressDTO updateAddress(Long addressId, @Valid AddressDTO addressDTO);

    String deleteAddress(Long addressId);
}
