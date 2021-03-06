import { Injectable } from '@angular/core';
import { HttpClient ,HttpHeaders} from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/api/product';
  private urlImg = 'http://localhost:8080/api/productimg';
  private urlImgList = 'http://localhost:8080/api/productimglist';
  private urlImg2 = 'http://localhost:8080/api/productimgA';

  private urlByType = 'http://localhost:8080/api/producttype';

  private urlImgListLimit = 'http://localhost:8080/api/productimglistlimit';
  private urltotal = 'http://localhost:8080/api/producttotal';
  private urlPageAsc = 'http://localhost:8080/api/products/pageasc';
  private urlPageDesc = 'http://localhost:8080/api/products/pagedesc';
  private urlPage = 'http://localhost:8080/api/products/page';



  private _refresh = new Subject<void>();
  private headers= new HttpHeaders({
    'Content-Type': 'application/json',
    //'x-access-token':localStorage.getItem('token'),
    //'Authorization': 'Bearer' + localStorage.getItem('token')
  })

  private headerImg= new HttpHeaders({
    //'x-access-token':localStorage.getItem('token'),
    //'Authorization': 'Bearer' + localStorage.getItem('token')
  })

  private options = { headers: this.headers };
  private optionsImg = { headers: this.headerImg };
  
  constructor(private http: HttpClient) { }
 
  get refresh(){
    return this._refresh;
  }

  total(): Observable<any>{
    return this.http.get(`${this.urltotal}`, this.options);
  }

  getList(): Observable<any>{
    return this.http.get(`${this.baseUrl}`, this.options);
  }
  

  getListPageAsc(pagenum: number): Observable<any>{
    return this.http.get(`${this.urlPageAsc}/${pagenum}`, this.options);
  }

  getListPageDesc(pagenum: number): Observable<any>{
    return this.http.get(`${this.urlPageDesc}/${pagenum}`, this.options);
  }

  getListPage(pagenum: number): Observable<any>{
    return this.http.get(`${this.urlPage}/${pagenum}`, this.options);
  }



  getById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, this.options);
  }

  getProductImgByProductId(id: number): Observable<any> {
    return this.http.get(`${this.urlImgList}/${id}`, this.options);
  }

  getProductImgByProductIdLimit(id: number): Observable<any> {
    return this.http.get(`${this.urlImgListLimit}/${id}`, this.options);
  }

  getByType(id: number): Observable<any> {
    return this.http.get(`${this.urlByType}/${id}`, this.options);
  }

  
  createNew(ob: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, ob, this.options).pipe(
      tap(()=> {
        this._refresh.next();
      })
    );
  }

  createProductImg(id: number,ob: FormData): Observable<Object> {
    return this.http.post(`${this.urlImg}/${id}`, ob, this.optionsImg).pipe(
      tap(()=> {
        this._refresh.next();
      })
    );
  }

  createProductImg2(id: number,ob: FormData): Observable<Object> {
    return this.http.post(`${this.urlImg2}/${id}`, ob, this.optionsImg).pipe(
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

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`,this.options);
  }

}
