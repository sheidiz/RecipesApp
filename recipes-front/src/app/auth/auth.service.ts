import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';

interface ResponseLoginDto {
  id: number;
  token: string;
  expiresIn: number;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/authentication';
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(this.hasValidToken());

  constructor(private http: HttpClient) { }

  login(userData: { email: string; password: string }): Observable<ResponseLoginDto> {
    return this.http.post<ResponseLoginDto>(`${this.apiUrl}/login`, userData).pipe(
      tap(response => this.handleLoginResponse(response))
    );
  }

  register(userData: { email: string; password: string, name: string; surname: string }): Observable<any> {
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

  private handleLoginResponse(response: ResponseLoginDto): void {
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
