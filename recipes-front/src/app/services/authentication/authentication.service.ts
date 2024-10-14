import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  // eslint-disable-next-line @typescript-eslint/no-empty-function
  constructor() {}

  isLoggedIn() {
    const token = localStorage.getItem('token');

    if (!token) return false;

    const payload = atob(token.split('.')[1]);

    const parsedPayload = JSON.parse(payload);

    return parsedPayload.exp > Date.now() / 1000;
  }
}
