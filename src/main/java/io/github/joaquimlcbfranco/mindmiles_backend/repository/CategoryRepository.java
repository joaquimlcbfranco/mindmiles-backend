package io.github.joaquimlcbfranco.mindmiles_backend.repository;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM categories WHERE user_id = ?1", nativeQuery = true)
    List<Category> findAllCategoriesByUserId(long id);
}
