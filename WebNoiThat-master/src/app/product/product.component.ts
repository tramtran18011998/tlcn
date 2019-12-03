import { Component, OnInit } from '@angular/core';
import { CategoryTypeService } from '../services/category-type.service';
import { Observable } from 'rxjs';
import { CategoryType } from '../models/categorytype';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  categoryTypes : Observable<CategoryType[]>;
  constructor(private categoryTypeService: CategoryTypeService) { }

  ngOnInit() {
    this.getList();
  }
  getList(){

    this.categoryTypes = this.categoryTypeService.getCategoryTypesList();
  }

}
