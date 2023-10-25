import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SignupServiceService {
  /*** Service that sends a post request to the server for the SignUp function ***/

  private baseUrl = 'http://localhost:8080'; 

  constructor(private http: HttpClient) { }

  signup(userDTO: any): Observable<any> {
    const url = `${this.baseUrl}/user/signup`;
    return this.http.post(url, userDTO);
  }
}
