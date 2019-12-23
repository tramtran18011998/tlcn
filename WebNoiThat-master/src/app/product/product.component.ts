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
import { Router } from '@angular/router';

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

  ps: Product[]=[];
  productImages: ProductImage[]=[];
  pis: ProductImage[]=[];

  asc: string;
  desc: string;
  default: string;

  arr=[];
  imgname=[];
  imgPush=[];
  x: number;

  productImg: ProductImage= new ProductImage();


  constructor(private productService: ProductService,private categoryTypeService: CategoryTypeService, private categoryService:CategoryService,private router: Router) { }

  ngOnInit() {

    this.productService.total().subscribe(data =>{
      this.totalProduct = data;
      console.log(this.totalProduct);
    })

    this.getList();
    this.getProductList();
    //this.geta();
  }
  getList(){

    this.categoryTypeService.getList().subscribe(data =>{
      this.categoryTypes = data;

    });
  }

  geta(){
    // this.productService.getListPage(0).subscribe(data => {
    //   this.ps = data.content;
    //   for(let i=0; i<this.ps.length; i++){
    //     this.productService.getProductImgByProductId(this.ps[i].id).subscribe(data => {
    //       this.pis = data;
    //       console.log(data);
    //       //console.log(this.pis.length-1);
    //       this.arr.push(this.pis[0]);
    //       console.log(this.arr);
    //     })
    //   }
    // })

    // this.productService.getList().subscribe(data => {
    //   this.products = data.content;
    //   for(let i=0; i<this.ps.length; i++){
    //     this.productService.getProductImgByProductId(this.ps[i].id).subscribe(data => {
    //       this.productImages = data;
    //       console.log(data);
    //       //console.log(this.pis.length-1);
    //       this.arr.push(this.pis[0]);
    //       console.log(this.arr);
    //     })
    //   }
    // })
    this.productService.getList().subscribe(data => {
      this.products = data;
      //console.log(data.content);
 
      this.imgname = [];

      var b =[];
      var a: number;
      for(let i=0; i<this.products.length; i++){
        console.log('ttt:', this.products[i].id);
        b.push(this.products[i].id);
        a= this.products[i].id;
        console.log('a=',a);
        this.productService.getProductImgByProductIdLimit(a).subscribe(data => {
        
          console.log('tt1:', this.products[i].id);
          //console.log('bb:', b);
          this.productImg = data;
          console.log(this.productImg.name);          
          this.imgname.push(this.productImg.name)  ;         
          //console.log(this.imgname[i]);
        })                    
      }
      this.imgPush = this.imgname;
      console.log(this.imgPush);
      console.log(this.imgPush.length);
    })
  }
  
  getProductList(){
    console.log(this.intSelect);
    if(this.intSelect==1){
      this.productService.getListPage(0).subscribe(data => {
        this.products = data.content;
        console.log(data.content);
   
        this.imgname = [];
  
        var b =[];
        var a: number;
        for(let i=0; i<this.products.length; i++){
          console.log('ttt:', this.products[i].id);
          b.push(this.products[i].id);
          a= this.products[i].id;
          //console.log('a=',b);
          this.productService.getProductImgByProductIdLimit(a).subscribe(data => {
            console.log('tt1:', this.products[i].id);
            //console.log('bb:', b);
            this.productImg = data;
            console.log(this.productImg.name);          
            this.imgname.push(this.productImg.name)  ;         
            //console.log(this.imgname[i]);
          })                    
        }
        // for(let i =0 ; i<b.length; i++){
        //   this.productService.getProductImgByProductIdLimit(b[i]).subscribe(data => {
        //     console.log('tt1:', this.products[i].id);
        //     console.log('bb:', b[i]);
        //     this.productImg = data;
        //     console.log(this.productImg.name);          
        //     this.imgname.push(this.productImg.name)           
        //     //console.log(this.imgname[i]);
        //   })  
        // }
        
        console.log(this.imgname);
      })
    }else if(this.intSelect ==2){
      this.productService.getListPageAsc(0).subscribe(data => {
        this.products = data.content;
        console.log(data.content);
        this.imgname = [];
  
        for(let i=0; i<this.products.length; i++){
          console.log('ttt:', this.products[i].id);
          this.productService.getProductImgByProductIdLimit(this.products[i].id).subscribe(data => {
            
            this.productImg = data;
            console.log(this.productImg.name);          
            this.imgname.push(this.productImg.name)           
            console.log(this.imgname.length);
          })                    
        }
      })
    }else{
      this.productService.getListPageDesc(0).subscribe(data => {
        this.products = data.content;
        console.log(data.content);
        this.imgname = [];
  
        for(let i=0; i<this.products.length; i++){
          console.log('ttt:', this.products[i].id);
          this.productService.getProductImgByProductIdLimit(this.products[i].id).subscribe(data => {
            
            this.productImg = data;
            console.log(this.productImg.name);          
            this.imgname.push(this.productImg.name)           
            console.log(this.imgname.length);
          })                    
        }
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
        
        this.imgname = [];
        for(let i=0; i<this.products.length; i++){
          console.log('ttt:', this.products[i].id);
          this.productService.getProductImgByProductIdLimit(this.products[i].id).subscribe(data => {           
            this.productImg = data;
            console.log(this.productImg.name);
            
            console.log(this.imgname.length);           
            this.imgname.push(this.productImg.name)         
          })          
        }
        
      });
    }else if(this.intSelect==2){
      this.productService.getListPageAsc(event.pageIndex).subscribe(data => {
        this.products = data.content;
        console.log(event.pageIndex);
        this.pageIn = event.pageIndex;
        console.log(this.pageIn);  
  
        for(let i=0; i<this.products.length; i++){
          console.log('ttt:', this.products[i].id);
          this.productService.getProductImgByProductIdLimit(this.products[i].id).subscribe(data => {           
            this.productImg = data;
            console.log(this.productImg.name);
            
            console.log(this.imgname.length);           
          })          
        }
      
      });
    }else{
      this.productService.getListPageDesc(event.pageIndex).subscribe(data => {
        this.products = data.content;
        console.log(event.pageIndex);
        this.pageIn = event.pageIndex;
        console.log(this.pageIn);  
       
        for(let i=0; i<this.products.length; i++){
          console.log('ttt:', this.products[i].id);
          this.productService.getProductImgByProductIdLimit(this.products[i].id).subscribe(data => {           
            this.productImg = data;
            console.log(this.productImg.name);
              
            console.log(this.imgname.length);           
          })          
        }
        
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

  detail(id: number){    
    this.router.navigate(['/productdetail',id]);
    console.log(id);
  }

}
