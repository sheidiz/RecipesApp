package com.sheiladiz.recipes.repository;

import com.sheiladiz.recipes.entity.Category;
import com.sheiladiz.recipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Override
    List<Recipe> findAll();

    List<Recipe> findByCategory(Category category);

    @Query("SELECT r FROM Recipe r WHERE " +
            "LOWER(r.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.ingredients) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Recipe> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT r FROM Recipe r WHERE " +
            "(LOWER(r.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.ingredients) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "r.category = :category")
    List<Recipe> searchByKeywordAndCategory(@Param("keyword") String keyword,
                                            @Param("category") Category category);
}
