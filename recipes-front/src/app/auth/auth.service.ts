import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/authentication';

  constructor(private http: HttpClient) {}

  login(userData: { email: string; password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, userData);
  }

  register(userData: { email: string; password: string, name:string; surname: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, userData);
  }
}
