package com.sheiladiz.recipes.controller;

import com.sheiladiz.recipes.dto.RecipeDto;
import com.sheiladiz.recipes.dto.RequestRecipeDto;
import com.sheiladiz.recipes.entity.Category;
import com.sheiladiz.recipes.entity.User;
import com.sheiladiz.recipes.service.RecipeService;
import com.sheiladiz.recipes.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/recipes")
@RestController
public class RecipesController {
    private final UserService userService;
    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(Authentication authentication,
                                                  @Valid @RequestBody RequestRecipeDto requestRecipeDto) {
        User user = getUser(authentication);
        RecipeDto recipe = recipeService.saveRecipe(requestRecipeDto, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable("id") Long recipeId) {
        RecipeDto recipe = recipeService.getRecipeById(recipeId);

        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        List<RecipeDto> recipes = recipeService.getAllRecipes();

        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/my-recipes")
    public ResponseEntity<List<RecipeDto>> getMyRecipes(Authentication authentication) {
        User user = getUser(authentication);
        List<RecipeDto> recipes = recipeService.getAllRecipesByCreator(user);

        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/my-favorite-recipes")
    public ResponseEntity<List<RecipeDto>> getMyFavoriteRecipes(Authentication authentication) {
        User user = getUser(authentication);
        List<RecipeDto> recipes = recipeService.getAllFavoriteRecipesByUser(user);

        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/save-favorite/{recipeId}")
    public ResponseEntity<?> saveFavoriteRecipe(@PathVariable("recipeId") Long recipeId, Authentication authentication) {
        User user = getUser(authentication);
        recipeService.addRecipeToUserFavorites(user, recipeId);

        return ResponseEntity.ok("Receta añadida a favoritos.");
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<RecipeDto>> getRecipesByCategory(@PathVariable("categoryName") Category category) {
        List<RecipeDto> recipes = recipeService.searchByCategory(category);

        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<RecipeDto>> getRecipesByKeyword(@PathVariable("keyword") String keyword) {
        List<RecipeDto> recipes = recipeService.searchByKeyword(keyword);

        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/category/{categoryName}/search/{keyword}")
    public ResponseEntity<List<RecipeDto>> getRecipesByCategoryAndKeyword(@PathVariable("categoryName") Category category,
                                                                          @PathVariable("keyword") String keyword) {
        List<RecipeDto> recipes = recipeService.searchByKeywordAndCategory(keyword, category);

        return ResponseEntity.ok(recipes);
    }

    private User getUser(Authentication authentication) {
        String email = authentication.getName();

        return userService.getUserByEmail(email);
    }

}
