import { Component, OnInit,ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryType } from '../corecontrol/models/categorytype';
import { CategoryTypeService } from '../corecontrol/services/category-type.service';
import { CategoryService } from '../corecontrol/services/category.service';
import { Category } from '../corecontrol/models/category';
import { Product } from '../corecontrol/models/product';
import { ProductService } from '../corecontrol/services/product.service';
import { ProductImage } from '../corecontrol/models/productimage';

import { MatPaginator } from '@angular/material/paginator';
import { equal } from 'assert';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  totalProduct: number;
  productsPerPage = 6;
  pageIn : number;
  intSelect: number = 1;

  items = [
    {id: '1',name: 'default'},
    {id: '2',name: 'Giá thấp'},
    {id: '3',name: 'Giá cao'}
  ];

  categoryTypes: CategoryType[]= [];
  categories: Observable<Category[]>;
  catename: Array<Category>[];
  products: Product[]=[];

  productImages: ProductImage[]=[];

  asc: string;
  desc: string;
  default: string;

  imgname=[];
  x: number;

  productImg: ProductImage= new ProductImage();


  constructor(private productService: ProductService,private categoryTypeService: CategoryTypeService, private categoryService:CategoryService) { }

  ngOnInit() {

    this.productService.total().subscribe(data =>{
      this.totalProduct = data;
      console.log(this.totalProduct);
    })

    this.getList();
    this.getProductList();
  }
  getList(){

    this.categoryTypeService.getList().subscribe(data =>{
      this.categoryTypes = data;
   
    });
  }

  getProductList(){
    
    if(this.intSelect==1){
      this.productService.getListPage(0).subscribe(data => {
        this.products = data.content;
        console.log(data.content);
        const list=[];
  
        for(let i=0; i<this.products.length; i++){
          console.log('ttt:', this.products[i].id);
          this.productService.getProductImgByProductIdLimit(this.products[i].id).subscribe(data => {
            
            this.productImg = data;
            console.log(this.productImg.name);
            list.push(this.productImg.name);
            
            console.log(this.imgname.length);
          })
          
          
        }
        this.imgname = list;
        console.log(this.imgname.length);
      })
    }else if(this.intSelect ==2){
      this.productService.getListPageAsc(0).subscribe(data => {
        this.products = data.content;
        console.log(data.content);
        const list=[];
  
        for(let i=0; i<this.products.length; i++){
          console.log('ttt:', this.products[i].id);
          this.productService.getProductImgByProductIdLimit(this.products[i].id).subscribe(data => {
            
            this.productImg = data;
            console.log(this.productImg.name);
            list.push(this.productImg.name);
            
            console.log(this.imgname.length);
          })
          
          
        }
        this.imgname = list;
        console.log(this.imgname.length);
      })
    }else{
      this.productService.getListPageDesc(0).subscribe(data => {
        this.products = data.content;
        console.log(data.content);
        const list=[];
  
        for(let i=0; i<this.products.length; i++){
          console.log('ttt:', this.products[i].id);
          this.productService.getProductImgByProductIdLimit(this.products[i].id).subscribe(data => {
            
            this.productImg = data;
            console.log(this.productImg.name);
            list.push(this.productImg.name);
            
            console.log(this.imgname.length);
          })
          
          
        }
        this.imgname = list;
        console.log(this.imgname.length);
      })

    }
    
  }

  onChangedPage(event){

    if(this.intSelect==1){
      this.productService.getListPage(event.pageIndex).subscribe(data => {
        this.products = data.content;
        console.log(event.pageIndex);
        this.pageIn = event.pageIndex;
        console.log(this.pageIn);  
        const list=[];  
        for(let i=0; i<this.products.length; i++){
          console.log('ttt:', this.products[i].id);
          this.productService.getProductImgByProductIdLimit(this.products[i].id).subscribe(data => {           
            this.productImg = data;
            console.log(this.productImg.name);
            list.push(this.productImg.name);           
            console.log(this.imgname.length);           
          })          
        }
        this.imgname = list;    
      });
    }else if(this.intSelect==2){
      this.productService.getListPageAsc(event.pageIndex).subscribe(data => {
        this.products = data.content;
        console.log(event.pageIndex);
        this.pageIn = event.pageIndex;
        console.log(this.pageIn);  
        const list=[];  
        for(let i=0; i<this.products.length; i++){
          console.log('ttt:', this.products[i].id);
          this.productService.getProductImgByProductIdLimit(this.products[i].id).subscribe(data => {           
            this.productImg = data;
            console.log(this.productImg.name);
            list.push(this.productImg.name);           
            console.log(this.imgname.length);           
          })          
        }
        this.imgname = list;    
      });
    }else{
      this.productService.getListPageDesc(event.pageIndex).subscribe(data => {
        this.products = data.content;
        console.log(event.pageIndex);
        this.pageIn = event.pageIndex;
        console.log(this.pageIn);  
        const list=[];  
        for(let i=0; i<this.products.length; i++){
          console.log('ttt:', this.products[i].id);
          this.productService.getProductImgByProductIdLimit(this.products[i].id).subscribe(data => {           
            this.productImg = data;
            console.log(this.productImg.name);
            list.push(this.productImg.name);           
            console.log(this.imgname.length);           
          })          
        }
        this.imgname = list;    
      });
    }

    

  }

  getCateByType(id: number){
    this.categories=this.categoryService.getByType(id);   
  }

  getImg(id: number){
    this.productService.getProductImgByProductIdLimit(id).subscribe(data => {
      this.productImg = data;
      console.log(this.productImg.name);
    })
    
  }
  loadProductByType(id: number){
    this.productService.getByType(id).subscribe(data =>{
      console.log(data);
    })
  }

  onOptionsSelected(value: number){

    this.intSelect = value;
    console.log(this.intSelect);
    this.getProductList();
  }

}
