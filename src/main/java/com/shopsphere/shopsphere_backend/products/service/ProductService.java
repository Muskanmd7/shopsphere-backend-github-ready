package com.shopsphere.shopsphere_backend.products.service;

import com.shopsphere.shopsphere_backend.exception.ResourcenotFound;
import com.shopsphere.shopsphere_backend.products.model.Product;
import com.shopsphere.shopsphere_backend.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllproducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Integer id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ResourcenotFound("Product Not Found"));

    }

    public Product updateProduct(Product product, Integer id) {

        productRepository.findById(id)
                .orElseThrow(() -> new ResourcenotFound("Product Not Found"));

        product.setProductId(id);

        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {

        productRepository.findById(id)
                .orElseThrow(() -> new ResourcenotFound("Product Not Found"));

        productRepository.deleteById(id);
    }

    public List<Product> searchProduct(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword);
    }

    public List<Product> getProductByCategory(String category) {
        return productRepository.findByProductCategory_Name(category);
    }

    public List<Product> filterByPrice(Double minPrice,
                                       Double maxPrice) {

        return productRepository.findByProductPriceBetween(minPrice, maxPrice);

    }

    public List<Product> sortByPriceAscending() {

        return productRepository.findAllByOrderByProductPriceAsc();

    }

    public List<Product> sortByPriceDescending() {

        return productRepository.findAllByOrderByProductPriceDesc();

    }

    public List<Product> sortByRating() {

        return productRepository.findAllByOrderByProductRatingDesc();

    }
}