import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { authGuard } from './auth/auth.guard';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'registro', component: RegisterComponent, canActivate: [authGuard] },
  { path: 'inicio-sesion', component: LoginComponent, canActivate: [authGuard] },
  // { path: 'recetas', component: RecipeDetailsComponent },
  // { path: 'receta/:id', component: RecipeDetailsComponent, canActivate: [authGuard] },
  // { path: 'nueva-receta', component: RecipeFormComponent, canActivate: [authGuard] },
  // { path: 'perfil/favoritos', component: RecipeDetailsComponent, canActivate: [authGuard] },
  // { path: 'perfil/recetas', component: RecipeDetailsComponent, canActivate: [authGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
