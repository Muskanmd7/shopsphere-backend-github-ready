package com.shopsphere.shopsphere_backend.address;

import com.shopsphere.shopsphere_backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

    List<Address> findByUser(User user);

}
