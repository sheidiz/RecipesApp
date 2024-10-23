import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RecipesComponent } from './recipes/recipes.component';
import { CreateRecipeComponent } from './create-recipe/create-recipe.component';
import { CommentsComponent } from './comments/comments.component';
import { RecipeComponent } from './recipe/recipe.component';
import { ProfileRecipesComponent } from './profile-recipes/profile-recipes.component';
import { MatTabsModule } from '@angular/material/tabs';
import {MatIconModule} from '@angular/material/icon';

@NgModule({
  declarations: [
    RecipesComponent,
    CreateRecipeComponent,
    CommentsComponent,
    RecipeComponent,
    ProfileRecipesComponent
  ],
  imports: [
    CommonModule,
    MatTabsModule,
    MatIconModule
  ]
})
export class RecipesModule { }
