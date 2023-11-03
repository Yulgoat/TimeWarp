import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ChangePwdService {
  /*** Service that sends a post request to the server for the ChangePwd function ***/

  private baseUrl = 'http://localhost:4200/serverapi/'; 

  constructor(private http: HttpClient) { }

  changepwd(ChangePwdDTO: any): Observable<any> {
    const url = `${this.baseUrl}/user/changepwd`;
    return this.http.patch(url, ChangePwdDTO);
  }
}