import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryType } from '../corecontrol/models/categorytype';
import { CategoryTypeService } from '../corecontrol/services/category-type.service';
import { CategoryService } from '../corecontrol/services/category.service';
import { Category } from '../corecontrol/models/category';

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
  constructor(private categoryTypeService: CategoryTypeService, private categoryService:CategoryService) { }

  ngOnInit() {
    this.getList();
    //this.getCategoryData();
  }
  getList(){

    //this.categoryTypes = this.categoryTypeService.getList();
    this.categoryTypeService.getList().subscribe(data =>{
      this.categoryTypes = data;
      
    });
  }

  getCateByType(id: number){
    this.categories=this.categoryService.getAllByCategoryType(id);
    //localStorage.setItem('a', JSON.stringify(this.categories));
    
    // console.log(this.categories);
    //this.catename = this.categories;
    
  }
  // getById(id: number){
  //   this.categoryTypeService.getById(id).subscribe(data => {
  //     console.log(data)
  //     //this.loaisp = data;
  //   }, error => console.log(error));
    
  // }


}
