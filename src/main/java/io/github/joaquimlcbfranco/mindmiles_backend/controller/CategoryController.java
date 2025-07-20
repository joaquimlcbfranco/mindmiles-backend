package io.github.joaquimlcbfranco.mindmiles_backend.controller;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.Category;
import io.github.joaquimlcbfranco.mindmiles_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return this.categoryService.getCategories();
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable long id) {
        return this.categoryService.getCategoryById(id);
    }

    @PostMapping("/users/{id}/categories")
    public Category addCategory(@PathVariable long id , @RequestBody Category newCategory) {
        return this.categoryService.addCategory(id, newCategory);
    }


}
