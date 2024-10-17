import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RecipesComponent } from './recipes/recipes.component';
import { FavoriteRecipesComponent } from './favorite-recipes/favorite-recipes.component';
import { MyRecipesComponent } from './my-recipes/my-recipes.component';
import { CreateRecipeComponent } from './create-recipe/create-recipe.component';
import { CommentsComponent } from './comments/comments.component';



@NgModule({
  declarations: [
    RecipesComponent,
    FavoriteRecipesComponent,
    MyRecipesComponent,
    CreateRecipeComponent,
    CommentsComponent
  ],
  imports: [
    CommonModule
  ]
})
export class RecipesModule { }
