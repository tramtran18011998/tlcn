import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryType } from '../corecontrol/models/categorytype';
import { CategoryTypeService } from '../corecontrol/services/category-type.service';
import { CategoryService } from '../corecontrol/services/category.service';
import { Category } from '../corecontrol/models/category';
import { Product } from '../corecontrol/models/product';
import { ProductService } from '../corecontrol/services/product.service';
import { ProductImage } from '../corecontrol/models/productimage';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  //categoryTypes : Observable<CategoryType[]>;
  categoryTypes: CategoryType[]= [];
  categories: Observable<Category[]>;
  catename: Array<Category>[];
  products: Product[]=[];

  imgname=[];

  productImg: ProductImage= new ProductImage();


  constructor(private productService: ProductService,private categoryTypeService: CategoryTypeService, private categoryService:CategoryService) { }

  ngOnInit() {
    this.getList();
    //this.getCategoryData();
    // this.productService.getList().subscribe(data => {
    //   this.products = data;
    //   console.log(this.products);
    // })
    this.getProductList();
  }
  getList(){

    //this.categoryTypes = this.categoryTypeService.getList();
    this.categoryTypeService.getList().subscribe(data =>{
      this.categoryTypes = data;
      
      
    });
  }

  getProductList(){
    this.productService.getList().subscribe(data => {
      console.log(data);
      this.products = data;
      for(let i=0; i<this.products.length; i++){
        //this.getImg(this.products[i].id);
        //console.log('ttt:', this.products[i].id);
        this.productService.getProductImgByProductIdLimit(this.products[i].id).subscribe(data => {
          this.productImg = data;
          console.log(this.productImg.name);
          this.imgname.push(this.productImg.name);
        })
        
      }
    })
  }

  getCateByType(id: number){
    this.categories=this.categoryService.getByType(id);
    //localStorage.setItem('a', JSON.stringify(this.categories));
    
    // console.log(this.categories);
    //this.catename = this.categories;
    
  }

  getImg(id: number){
    this.productService.getProductImgByProductIdLimit(id).subscribe(data => {
      //console.log(data);
      this.productImg = data;
      console.log(this.productImg.name);
    })
    
  }
  loadProductByType(id: number){
    this.productService.getByType(id).subscribe(data =>{
      console.log(data);
    })
  }

}
