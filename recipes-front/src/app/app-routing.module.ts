import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './pages/auth/register/register.component';

const routes: Routes = [
  { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: 'registro', component: RegisterComponent },
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
