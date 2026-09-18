package com.shopsphere.shopsphere_backend.payment;

import com.shopsphere.shopsphere_backend.orders.Orders;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    private Double amount;
   @NotBlank
    private String paymentMethod;
   @NotBlank
    private String paymentStatus;

    private LocalDateTime paymentDate;

    private String transactionId;
}
