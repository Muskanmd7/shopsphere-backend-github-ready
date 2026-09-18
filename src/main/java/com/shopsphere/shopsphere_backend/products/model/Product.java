package com.shopsphere.shopsphere_backend.products.model;

import com.shopsphere.shopsphere_backend.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Product description is required")
    private String productDescription;

    @NotNull(message = "Product price is required")
    @Positive(message = "Price must be greater than 0")
    private Double productPrice;

    @NotNull(message = "Product quantity is required")
    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer productQuantity;

    @NotNull(message = "Category is required")
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category productCategory;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @DecimalMin(value = "0.0", message = "Rating cannot be negative")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5")
    private Double productRating;




}
