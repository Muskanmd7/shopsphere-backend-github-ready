package com.shopsphere.shopsphere_backend.payment;

import com.shopsphere.shopsphere_backend.exception.ResourcenotFound;
import com.shopsphere.shopsphere_backend.orders.Orders;
import com.shopsphere.shopsphere_backend.orders.ordersRepo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private OrderRepo orderRepo;

    public Payment createPayment(Long orderId,
                                 String paymentMethod) {

        Orders order = orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourcenotFound("Order Not Found"));

        Payment payment = new Payment();

        payment.setOrder(order);
        payment.setAmount(order.getTotalPrice());
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus("PENDING");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setTransactionId(UUID.randomUUID().toString());

        return paymentRepo.save(payment);

    }

    public Payment getPayment(Integer paymentId) {

        return paymentRepo.findById(paymentId)
                .orElseThrow(() -> new ResourcenotFound("Payment Not Found"));

    }

    public Payment updatePaymentStatus(Integer paymentId,
                                       String status) {

        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new ResourcenotFound("Payment Not Found"));

        payment.setPaymentStatus(status);

        return paymentRepo.save(payment);

    }

    public List<Payment> getAllPayments() {

        return paymentRepo.findAll();

    }

}