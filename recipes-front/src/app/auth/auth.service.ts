import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { ResponseUserLogin } from '../models/user/ResponseUserLogin';
import { RequestUserRegister } from '../models/user/RequestUserRegister';
import { RequestUserLogin } from '../models/user/RequestUserLogin';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/authentication';
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(this.hasValidToken());

  constructor(private http: HttpClient) { }

  login(userData: RequestUserLogin): Observable<ResponseUserLogin> {
    return this.http.post<ResponseUserLogin>(`${this.apiUrl}/login`, userData).pipe(
      tap(response => this.handleLoginResponse(response))
    );
  }

  register(userData: RequestUserRegister): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, userData);
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('tokenExpiration');
    this.isAuthenticatedSubject.next(false);
  }

  isAuthenticated(): Observable<boolean> {
    return this.isAuthenticatedSubject.asObservable();
  }

  private handleLoginResponse(response: ResponseUserLogin): void {
    this.storeToken(response.token, response.expiresIn);
    this.isAuthenticatedSubject.next(true);
  }

  private storeToken(token: string, expiresIn: number): void {
    localStorage.setItem('token', token);
    const expirationDate = new Date().getTime() + expiresIn * 1000;
    localStorage.setItem('tokenExpiration', expirationDate.toString());
  }

  private hasValidToken(): boolean {
    const token = localStorage.getItem('token');
    const expiration = localStorage.getItem('tokenExpiration');

    if (token && expiration) {
      const expirationDate = parseInt(expiration, 10);
      return expirationDate > new Date().getTime();
    }

    return false;
  }

}
