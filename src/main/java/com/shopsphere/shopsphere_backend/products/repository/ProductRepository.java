package com.shopsphere.shopsphere_backend.products.repository;

import com.shopsphere.shopsphere_backend.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductNameContainingIgnoreCase(String keyword);
    List<Product>findByProductCategory_Name(String category);
    List<Product> findByProductPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findAllByOrderByProductPriceAsc();

    List<Product> findAllByOrderByProductPriceDesc();

    List<Product> findAllByOrderByProductRatingDesc();
}
