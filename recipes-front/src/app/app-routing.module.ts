import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { authGuard } from './auth/auth.guard';
import { HomeComponent } from './home/home.component';
import { RecipesComponent } from './recipes/recipes/recipes.component';
import { RecipeComponent } from './recipes/recipe/recipe.component';
import { CreateRecipeComponent } from './recipes/create-recipe/create-recipe.component';
import { ProfileRecipesComponent } from './recipes/profile-recipes/profile-recipes.component';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'registro', component: RegisterComponent, canActivate: [authGuard] },
  { path: 'inicio-sesion', component: LoginComponent, canActivate: [authGuard] },
  { path: 'recetas', component: RecipesComponent, canActivate: [authGuard]  },
  { path: 'receta/:id', component: RecipeComponent, canActivate: [authGuard] },
  { path: 'nueva-receta', component: CreateRecipeComponent, canActivate: [authGuard] },
  { path: 'perfil', component: ProfileRecipesComponent, canActivate: [authGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
