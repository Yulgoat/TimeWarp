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
export class SignOutService {

  private baseUrl = 'http://localhost:4200/serverapi/'; 

 
  constructor(private http: HttpClient) { }

  signOut(): Observable<any> {
    const url = `${this.baseUrl}/user/signout`;    
    return this.http.post(url, null);
  }


  
}