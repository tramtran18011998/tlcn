import { Injectable } from '@angular/core';
import { HttpClient ,HttpHeaders} from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import {tap} from 'rxjs/operators'


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = 'http://localhost:8080/api/customer';
  private urlUser = 'http://localhost:8080/api/user';
  private urlUser2 = 'http://localhost:8080/api/users';
  private url = 'http://localhost:8080/api/customeruser';
  private urlImgae = 'http://localhost:8080/api/userimg';
  private urlU = 'http://localhost:8080/api/employeeuser';

  private _refresh = new Subject<void>();
  private headers= new HttpHeaders({
    'Content-Type': 'application/json',
    //'x-access-token':localStorage.getItem('token'),
    //'Authorization': 'Bearer' + localStorage.getItem('token')
  })
  private headImg = new HttpHeaders({
    //'x-access-token':localStorage.getItem('token'),
    //'Authorization': 'Bearer' + localStorage.getItem('token')
  })
  private options = { headers: this.headers };
  private optionsImg = {headers: this.headImg};
  
  constructor(private http: HttpClient) { }
 
  get refresh(){
    return this._refresh;
  }
  getList(): Observable<any>{
    return this.http.get(`${this.baseUrl}`, this.options);
  }
  
  getImg(imgname: string): Observable<any>{
    return this.http.get(`${this.urlImgae}/${imgname}`, this.optionsImg);
  }

  getIdByUserId(id: number): Observable<any> {
    return this.http.get(`${this.url}/${id}`, this.options);
  }

  getById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, this.options);
  }

  getUserById(id: number): Observable<any> {
    return this.http.get(`${this.urlUser}/${id}`, this.options);
  }

  
  createNew(ob: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, ob, this.options).pipe(
      tap(()=> {
        this._refresh.next();
      })
    );
  }

  updateUser(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.urlU}/${id}`, value, this.options).pipe(
      tap(()=> {
        this._refresh.next();
      })
    );
  }

  update(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value, this.options).pipe(
      tap(()=> {
        this._refresh.next();
      })
    );
  }

  updateImg(fd: FormData,id: number): Observable<Object> {
    return this.http.put(`${this.urlImgae}/${id}`, fd, this.optionsImg).pipe(
      tap(()=> {
        this._refresh.next();
      })
    );
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`,this.options);
  }
}
