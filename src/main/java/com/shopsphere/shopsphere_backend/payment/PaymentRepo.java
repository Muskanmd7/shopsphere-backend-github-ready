package com.shopsphere.shopsphere_backend.payment;

import com.shopsphere.shopsphere_backend.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

    Optional<Payment> findByOrder(Orders order);

}