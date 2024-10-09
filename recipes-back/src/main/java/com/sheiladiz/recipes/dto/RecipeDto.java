package com.sheiladiz.recipes.dto;

import com.sheiladiz.recipes.entity.Category;

import java.time.LocalDateTime;

public record RecipeDto(
        Long id,
        String title,
        String origin,
        String description,
        String ingredients,
        String instructions,
        int servings,
        int cookTime,
        int prepTime,
        String imageUrl,
        String videoUrl,
        String creatorName,
        String creatorSurname,
        Category category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
