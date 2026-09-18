package com.shopsphere.shopsphere_backend.address;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public Address addAddress(@Valid @RequestBody Address address,
                              HttpServletRequest request) {

        return addressService.addAddress(address, request);
    }

    @GetMapping
    public List<Address> getMyAddresses(HttpServletRequest request) {

        return addressService.getMyAddresses(request);
    }

    @PutMapping("/{addressId}")
    public Address updateAddress(@PathVariable Integer addressId,
                                 @Valid @RequestBody Address address) {

        return addressService.updateAddress(addressId, address);
    }

    @DeleteMapping("/{addressId}")
    public void deleteAddress(@PathVariable Integer addressId) {

        addressService.deleteAddress(addressId);
    }

}
