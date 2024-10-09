package com.sheiladiz.recipes.mapper;

import com.sheiladiz.recipes.dto.RecipeDto;
import com.sheiladiz.recipes.dto.RequestRecipeDto;
import com.sheiladiz.recipes.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    @Mapping(source = "creator.name", target = "creatorName")
    @Mapping(source = "creator.surname", target = "creatorSurname")
    RecipeDto toRecipeDTO(Recipe recipe);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    Recipe requestRecipeToRecipe(RequestRecipeDto requestRecipeDto);

    List<RecipeDto> toRecipeDTOList(List<Recipe> recipes);

}
