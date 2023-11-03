import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:4200/serverapi/user';

  constructor(private http: HttpClient) { }

  getCurrentUser(): Observable<User> {
    const url = `${this.baseUrl}/currentuser`;

    return this.http.post<User>(url, {});
  }
}
