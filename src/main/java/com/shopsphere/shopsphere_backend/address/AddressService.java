package com.shopsphere.shopsphere_backend.address;



import com.shopsphere.shopsphere_backend.Jwtsecurity.JwtService;
import com.shopsphere.shopsphere_backend.exception.ResourcenotFound;
import com.shopsphere.shopsphere_backend.user.model.User;
import com.shopsphere.shopsphere_backend.user.Repository.userRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private userRepo userRepo;

    private User getLoggedInUser(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourcenotFound("User Not Found"));
    }

    // Add Address
    public Address addAddress(Address address,
                              HttpServletRequest request) {

        User user = getLoggedInUser(request);

        address.setUser(user);

        return addressRepo.save(address);
    }

    // Get My Addresses
    public List<Address> getMyAddresses(HttpServletRequest request) {

        User user = getLoggedInUser(request);

        return addressRepo.findByUser(user);
    }

    // Update Address
    public Address updateAddress(Integer addressId,
                                 Address updatedAddress) {

        Address address = addressRepo.findById(addressId)
                .orElseThrow(() -> new ResourcenotFound("Address Not Found"));

        address.setFullName(updatedAddress.getFullName());
        address.setMobileNumber(updatedAddress.getMobileNumber());
        address.setAddressLine1(updatedAddress.getAddressLine1());
        address.setAddressLine2(updatedAddress.getAddressLine2());
        address.setCity(updatedAddress.getCity());
        address.setState(updatedAddress.getState());
        address.setCountry(updatedAddress.getCountry());
        address.setPincode(updatedAddress.getPincode());

        return addressRepo.save(address);
    }

    // Delete Address
    public void deleteAddress(Integer addressId) {

        addressRepo.findById(addressId)
                .orElseThrow(() -> new ResourcenotFound("Address Not Found"));

        addressRepo.deleteById(addressId);
    }

}