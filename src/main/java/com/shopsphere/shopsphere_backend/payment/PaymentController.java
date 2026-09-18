package com.shopsphere.shopsphere_backend.payment;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{orderId}")
    public Payment createPayment(@PathVariable Long orderId,
                                 @Valid @RequestParam String paymentMethod){

        return paymentService.createPayment(orderId,paymentMethod);

    }

    @GetMapping("/{paymentId}")
    public Payment getPayment(@PathVariable Integer paymentId){

        return paymentService.getPayment(paymentId);

    }

    @GetMapping
    public List<Payment> getAllPayments(){

        return paymentService.getAllPayments();

    }

    @PutMapping("/{paymentId}")
    public Payment updatePaymentStatus(@PathVariable Integer paymentId,
                                       @Valid @RequestParam String status){

        return paymentService.updatePaymentStatus(paymentId,status);

    }

}
