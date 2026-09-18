package com.shopsphere.shopsphere_backend.orders.ordersRepo;

import com.shopsphere.shopsphere_backend.orders.Orders;
import com.shopsphere.shopsphere_backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {

    List<Orders> findByUser(User user);

}
