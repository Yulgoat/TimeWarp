import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map, Observable, of, Subject } from "rxjs";


interface UserNameDTO {
  user_name: string;
}

@Injectable({
  providedIn: 'root'
})


export class SigninServiceService {

  private baseUrl = 'http://localhost:4200/serverapi/'; 

  user: UserNameDTO={
    user_name: ''
  };

  /* Allow to know the actual user of the client */
  getActualUser(): Observable<UserNameDTO> {
    if (this.user.user_name !== '') {
      return of(this.user); 
    } else {
      return new Observable(observer => {
        this.principalUser().subscribe(
          (response) => {
            this.user.user_name = response.username;
            console.log("Alors peut-être");
            console.log(this.user);
            observer.next(this.user); 
            observer.complete(); 
          });
      });
    }
  }

  constructor(private http: HttpClient) {}


  signIn(userDTO: any): Observable<any> {
    const url = `${this.baseUrl}/user/signin`;
    return this.http.post(url, userDTO);
  }

  /* Set user to default because the user is disconnected */
  setActualUserToDefault(){
    this.user.user_name='';
  }

  /* Get the current user in the server */
  principalUser(): Observable<any> {
    const principalUser_url = `${this.baseUrl}/user/currentuser`;
    return this.http.post(principalUser_url,null);
  }
}