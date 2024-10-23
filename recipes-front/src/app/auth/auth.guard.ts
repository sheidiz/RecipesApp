import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const isAuthenticated = !!localStorage.getItem("token");
  const router = inject(Router);
  const url = state.url;

  if (isAuthenticated && (url.includes("registro") || url.includes("inicio-sesion"))) {
    router.navigate(['/']);
    return false;
  }

  if (isAuthenticated) {
    return true;
  } else {
    router.navigate(['/inicio-sesion']);
    return false;
  }
};
