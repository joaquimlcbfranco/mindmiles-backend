package io.github.joaquimlcbfranco.mindmiles_backend.service;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();

    Category getCategoryById(long id);

    List<Category> getCategoryByUserId(long id);

    Category addCategory(long id, Category category);

    Category updateCategory(long id, Category newCategory);

    String deleteCategory(long id);
}
