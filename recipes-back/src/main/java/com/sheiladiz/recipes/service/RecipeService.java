package com.sheiladiz.recipes.service;

import com.sheiladiz.recipes.dto.RecipeDto;
import com.sheiladiz.recipes.dto.RequestRecipeDto;
import com.sheiladiz.recipes.entity.Category;
import com.sheiladiz.recipes.entity.User;

import java.util.List;

public interface RecipeService {
    RecipeDto saveRecipe(RequestRecipeDto newRecipe, User user);

    List<RecipeDto> getAllRecipes();

    List<RecipeDto> searchByKeyword(String keyword);

    List<RecipeDto> searchByCategory(Category category);

    List<RecipeDto> searchByKeywordAndCategory(String keyword, Category category);

    RecipeDto getRecipeById(Long id);

}
