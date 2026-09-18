package com.shopsphere.shopsphere_backend.products.controller;

import com.shopsphere.shopsphere_backend.products.model.Product;
import com.shopsphere.shopsphere_backend.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllproducts() {
        return productService.getAllproducts();
    }

    @PostMapping("/products")
    public Product addProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@Valid @RequestBody Product product, @PathVariable Integer id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }
    @GetMapping("/products/search/{keyword}")
    public List<Product> searchProduct(@PathVariable String keyword) {
        return productService.searchProduct(keyword);
    }
    @GetMapping("products/category/{category}")
public List<Product> getProductByCategory(@PathVariable String category) {
    return productService.getProductByCategory(category);
}
    @GetMapping("/filter")
    public List<Product> filterByPrice(@Valid @RequestParam Double minPrice,
                                       @Valid @RequestParam Double maxPrice){

        return productService.filterByPrice(minPrice,maxPrice);

    }

    @GetMapping("/sort/priceAsc")
    public List<Product> sortByPriceAscending(){

        return productService.sortByPriceAscending();

    }

    @GetMapping("/sort/priceDesc")
    public List<Product> sortByPriceDescending(){

        return productService.sortByPriceDescending();

    }

    @GetMapping("/sort/rating")
    public List<Product> sortByRating(){

        return productService.sortByRating();

    }
}