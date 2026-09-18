package com.shopsphere.shopsphere_backend.category.categoryController;

import com.shopsphere.shopsphere_backend.category.Category;
import com.shopsphere.shopsphere_backend.category.CategoryService.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category saveCategory(@Valid @RequestBody Category category) {
        return categoryService.saveCategory(category);
    }
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    @GetMapping("/name/{name}")
    public Category getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
    @PutMapping("/{id}")
    public Category updateCategory(@Valid @RequestBody Category category, @PathVariable Long id) {
        return categoryService.updateCategory(category,id);
    }
    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

}
