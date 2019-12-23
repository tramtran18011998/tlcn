import { Injectable } from '@angular/core';
import {tap} from 'rxjs/operators'

import { HttpClient ,HttpHeaders } from '@angular/common/http';
import { Observable,Subject } from 'rxjs';
import { LoginRequest } from '../payloads/login-request';
import { SignupRequest } from '../payloads/signup-request';
import { ApiResponse } from '../payloads/api-response';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api';
  private baseUrlcheck = 'http://localhost:8080/api/loginCheck';
  rolename: string;

  private _refresh = new Subject<void>();
  private headers= new HttpHeaders({
    'Content-Type': 'application/json',
    //'x-access-token':localStorage.getItem('token'),
    //'Authorization': 'Bearer' + localStorage.getItem('token')
  })
  private options = { headers: this.headers };
  

  constructor(private http: HttpClient) { }

  login( credentials: LoginRequest){    
    return this.http.post(`${this.baseUrl}`+"/login",credentials,{ responseType: 'text' });
  }

  check(email: string): Observable<any> {
    return this.http.get(`${this.baseUrl+"/loginCheck"}/${email}`,{ responseType: 'text' });
    //${this.baseUrl}/${id}
  }

  getUserByEmail(email: string): Observable<any> {
    return this.http.get(`${this.baseUrl+"/userfind"}/${email}`, this.options);
  }
  signup(credentials: SignupRequest){
    return this.http.post(`${this.baseUrl}`+"/signup",credentials);
  }

}
