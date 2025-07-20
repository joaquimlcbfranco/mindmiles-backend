package io.github.joaquimlcbfranco.mindmiles_backend.service;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.Activity;
import io.github.joaquimlcbfranco.mindmiles_backend.entity.Category;
import io.github.joaquimlcbfranco.mindmiles_backend.exception.CategoryNotFoundException;
import io.github.joaquimlcbfranco.mindmiles_backend.exception.InvalidParametersException;
import io.github.joaquimlcbfranco.mindmiles_backend.repository.CategoryRepository;
import io.github.joaquimlcbfranco.mindmiles_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(long id) {

        Category foundCategory = this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));

        return foundCategory;
    }

    @Override
    @Transactional
    public Category addCategory(long id, Category category) {



    }


    public static boolean validateCategory(String name, String color) {

        if (name == null) {
            throw new InvalidParametersException("Category name is a required field");
        }

        if (color == null || !color.matches("^#?([a-f0-9]{6}|[a-f0-9]{3})$")) {
            throw new InvalidParametersException("Please provide a valid color for the category");
        }
    }
}
