package com.sheiladiz.recipes.dto;

import com.sheiladiz.recipes.entity.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestRecipeDto(
        @NotBlank(message = "El titulo es obligatorio y no puede estar vacío.")
        String title,
        String origin,
        @NotBlank(message = "La descripción es obligatoria y no puede estar vacía.")
        String description,
        @NotBlank(message = "La lista de ingredientes es obligatoria y no puede estar vacía.")
        String ingredients,
        @NotBlank(message = "La lista de instrucciones es obligatoria y no puede estar vacía.")
        String instructions,
        @NotNull
        @Min(value = 1, message = "La cantidad de porciones minima es 1.")
        int servings,
        int cookTime,
        int prepTime,
        String imageUrl,
        String videoUrl,
        @NotNull(message = "La categoría es obligatoria.")
        Category category
) {
}