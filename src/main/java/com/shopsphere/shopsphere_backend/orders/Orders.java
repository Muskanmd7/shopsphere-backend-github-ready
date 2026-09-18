package com.shopsphere.shopsphere_backend.orders;

import com.shopsphere.shopsphere_backend.products.model.Product;
import com.shopsphere.shopsphere_backend.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Orders {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long orderId;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;

        private Integer quantity;

        private Double totalPrice;

        private String orderStatus;

        private LocalDateTime orderDate;
    }

