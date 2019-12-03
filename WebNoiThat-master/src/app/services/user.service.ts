import { Injectable } from '@angular/core';
import { HttpClient ,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from '../payloads/login-request';
import { Data } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  

  private baseUrl = 'http://localhost:8080/api';
  private baseUrlcheck = 'http://localhost:8080/api/loginCheck';
  rolename: string;

  constructor(private http: HttpClient) { }

  login( credentials: LoginRequest){
    
    return this.http.post(`${this.baseUrl}`+"/login",credentials);
  }

  
  // loginCheck( login: LoginRequest): Observable<any>{    
   
  //   // const httpOptions = {
  //   //   headers: new HttpHeaders({
  //   //     'Content-Type': 'application/json'
  //   //   }),
  //   //   observe: 'response' as 'body'
  //   // };
  //   return this.http.get<any>(`${this.baseUrl}`+"/loginCheck",login);;
  // }
  // loginCheck( credentials: LoginRequest){
    
  //   return this.http.post(`${this.baseUrl}`+"/loginCheck", credentials);
  // }

  check(email: string): Observable<any> {
    return this.http.get(`${this.baseUrl+"/loginCheck"}/${email}`,{ responseType: 'text' });
    //${this.baseUrl}/${id}
  }

}
