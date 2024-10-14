import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: '/', pathMatch: 'full' },
  // { path: '/recetas', component: RecipeDetailsComponent },
  // { path: '/receta/:id', component: RecipeDetailsComponent, canActivate: [authGuard] },
  // { path: '/nueva-receta', component: RecipeFormComponent, canActivate: [authGuard] },
  // { path: '/perfil/favoritos', component: RecipeDetailsComponent, canActivate: [authGuard] },
  // { path: '/perfil/recetas', component: RecipeDetailsComponent, canActivate: [authGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
