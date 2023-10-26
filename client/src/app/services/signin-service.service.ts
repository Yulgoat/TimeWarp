import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

interface UserNameDTO {
  user_name: string;
}

@Injectable({
  providedIn: 'root'
})
export class SigninServiceService {

  private baseUrl = 'http://localhost:8080'; 

  user: UserNameDTO={
    user_name: ''
  };

  /* Allow to know the actual user of the client */
  get actual_user(): UserNameDTO{
    console.log("Service");
    console.log(this.user);
    return this.user;
  }

  constructor(private http: HttpClient) { }

  signIn(userDTO: any): Observable<any> {
    const url = `${this.baseUrl}/user/signin`;
    return this.http.post(url, userDTO);
  }

  /* Set the actual user of the client (when signIn is successful) */
  setActualUser(userNameDTO: UserNameDTO){
    this.user = userNameDTO;
  }
}