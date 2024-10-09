package com.sheiladiz.recipes.service.implementation;

import com.sheiladiz.recipes.dto.RecipeDto;
import com.sheiladiz.recipes.dto.RequestRecipeDto;
import com.sheiladiz.recipes.entity.Category;
import com.sheiladiz.recipes.entity.Recipe;
import com.sheiladiz.recipes.entity.User;
import com.sheiladiz.recipes.exception.ResourceNotFoundException;
import com.sheiladiz.recipes.mapper.RecipeMapper;
import com.sheiladiz.recipes.repository.RecipeRepository;
import com.sheiladiz.recipes.repository.UserRepository;
import com.sheiladiz.recipes.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final RecipeMapper recipeMapper;

    @Override
    public RecipeDto saveRecipe(RequestRecipeDto requestRecipeDto, User user) {
        Recipe recipe = recipeMapper.requestRecipeToRecipe(requestRecipeDto);
        recipe.setCreator(user);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return recipeMapper.toRecipeDTO(savedRecipe);
    }

    @Override
    public RecipeDto getRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (recipe.isPresent()) {
            return recipeMapper.toRecipeDTO(recipe.get());
        } else {
            throw new ResourceNotFoundException("No se encontro la receta.");
        }
    }

    @Override
    public List<RecipeDto> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipeMapper.toRecipeDTOList(recipes);
    }

    @Override
    public List<RecipeDto> getAllRecipesByCreator(User user) {
        List<Recipe> recipes = user.getRecipes();
        return recipeMapper.toRecipeDTOList(recipes);
    }

    @Override
    public List<RecipeDto> getAllFavoriteRecipesByUser(User user) {
        List<Recipe> recipes = user.getFavorites();
        return recipeMapper.toRecipeDTOList(recipes);
    }

    @Override
    public void addRecipeToUserFavorites(User user, Long recipeId){
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if (recipe.isPresent()) {
            List<Recipe> favorites = user.getFavorites();
            favorites.add(recipe.get());
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("No se encontro la receta.");
        }
    }

    @Override
    public List<RecipeDto> searchByKeyword(String keyword) {
        List<Recipe> recipes = recipeRepository.searchByKeyword(keyword);
        return recipeMapper.toRecipeDTOList(recipes);
    }

    @Override
    public List<RecipeDto> searchByCategory(Category category) {
        List<Recipe> recipes = recipeRepository.findByCategory(category);
        return recipeMapper.toRecipeDTOList(recipes);
    }

    @Override
    public List<RecipeDto> searchByKeywordAndCategory(String keyword, Category category) {
        List<Recipe> recipes = recipeRepository.searchByKeywordAndCategory(keyword, category);
        return recipeMapper.toRecipeDTOList(recipes);
    }


}
