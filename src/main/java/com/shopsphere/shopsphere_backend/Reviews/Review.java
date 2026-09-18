package com.shopsphere.shopsphere_backend.Reviews;

import com.shopsphere.shopsphere_backend.products.model.Product;
import com.shopsphere.shopsphere_backend.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    @NotBlank

    private String review;

    private LocalDateTime reviewDate;
}
