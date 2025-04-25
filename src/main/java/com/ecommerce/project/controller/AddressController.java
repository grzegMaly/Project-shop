package com.ecommerce.project.controller;

import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.Address.AddressDTO;
import com.ecommerce.project.payload.Address.AddressResponse;
import com.ecommerce.project.service.Address.AddressService;
import com.ecommerce.project.util.AuthUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AddressController {

    private final AuthUtil authUtil;
    private AddressService addressService;

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {

        User user = authUtil.loggedInUser();
        AddressDTO savedDto = addressService.createAddress(addressDTO, user);
        return ResponseEntity.ok().body(savedDto);
    }

    @GetMapping("/addresses")
    public ResponseEntity<AddressResponse> getAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long addressId) {
        return ResponseEntity.ok(addressService.getAddressById(addressId));
    }

    @GetMapping("/users/addresses")
    public ResponseEntity<AddressResponse> getUserAddresses() {

        User user = authUtil.loggedInUser();
        return ResponseEntity.ok(addressService.getUserAddresses(user));
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> createAddress(@PathVariable Long addressId,
                                                    @Valid @RequestBody AddressDTO addressDTO) {

        AddressDTO savedDto = addressService.updateAddress(addressId, addressDTO);
        return ResponseEntity.ok().body(savedDto);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
        return ResponseEntity.ok().body(addressService.deleteAddress(addressId));
    }
}
