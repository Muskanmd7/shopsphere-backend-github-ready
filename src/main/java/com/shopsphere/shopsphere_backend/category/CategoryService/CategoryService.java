package com.shopsphere.shopsphere_backend.category.CategoryService;

import com.shopsphere.shopsphere_backend.category.Category;
import com.shopsphere.shopsphere_backend.category.categoryRepo.CategoryRepo;
import com.shopsphere.shopsphere_backend.exception.ResourcenotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(Long id) {

        return categoryRepo.findById(id)
                .orElseThrow(() -> new ResourcenotFound("Category Not Found"));

    }

    public Category getCategoryByName(String name) {
        return categoryRepo.findByName(name)
                .orElseThrow(()->new ResourcenotFound("CATEGORY not available"));
    }

    public Category updateCategory(Category category, Long id) {

        categoryRepo.findById(id)
                .orElseThrow(() -> new ResourcenotFound("Category Not Found"));

        category.setId(id);

        return categoryRepo.save(category);
    }

    public void deleteCategoryById(Long id) {

        categoryRepo.findById(id)
                .orElseThrow(() -> new ResourcenotFound("Category Not Found"));

        categoryRepo.deleteById(id);
    }
}
