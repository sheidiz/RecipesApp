import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  isAuthenticated = false;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
  ) {
    this.isAuthenticated = this.authService.isLoggedIn();
  }

  logout() {
    localStorage.removeItem('token');
    this.isAuthenticated = false;
    this.router.navigate(['/iniciar-sesion']);
  }
}
