import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import {tap} from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class CategoryTypeService {

  private baseUrl = 'http://localhost:8080/api/categorytype';

  private _refresh = new Subject<void>();
  
  constructor(private http: HttpClient) { }
 
  get refresh(){
    return this._refresh;
  }
  getCategoryTypesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  

  getCategoryTypeById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createCategoryType(CategoryType: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, CategoryType).pipe(
      tap(()=> {
        this._refresh.next();
      })
    );
  }

  updateCategoryType(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value).pipe(
      tap(()=> {
        this._refresh.next();
      })
    );
  }

  // deleteCategoryType(id: number, tenloaisp: string): Observable<any> {
  //   return this.http.delete(`${this.baseUrl}/${id}/${tenloaisp}`, { responseType: 'text' });
  // }
}
