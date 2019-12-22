import { Component, OnInit,ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatSort } from '@angular/material';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { CategoryService } from 'src/app/corecontrol/services/category.service';
import { Category } from 'src/app/corecontrol/models/category';
import { CategoryTypeService } from 'src/app/corecontrol/services/category-type.service';
import { CategoryType } from 'src/app/corecontrol/models/categorytype';


@Component({
  selector: 'app-admin-category',
  templateUrl: './admin-category.component.html',
  styleUrls: ['./admin-category.component.css']
})
export class AdminCategoryComponent implements OnInit {

  displayedColumns: string[] = ['id','name','choose'];

  displayedColumns2: string[] = ['id','name'];
  categorys: Category[] =[];
  categoryTypes: CategoryType[]=[];

  dataSource ;
  dateSource2;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private categoryService: CategoryService, private categoryTypeService: CategoryTypeService, private router: Router) { }

  ngOnInit() {
    this.getListCategory();
    this.getListCategoryType();
  }

  getListCategory(){
    this.categoryService.getList().subscribe(data => {
      console.log(data);
      this.categorys = data;
      this.dataSource = new MatTableDataSource(this.categorys);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;

    })
  }
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  getListCategoryType(){
    this.categoryTypeService.getList().subscribe(data => {
      
      this.categoryTypes = data;
      console.log(this.categoryTypes);
      this.dateSource2 = new MatTableDataSource(this.categoryTypes);
      console.log(this.dateSource2);
    
    })
  }

  delete(id: number){

    Swal.fire({
      title: 'Bạn có chắc chắn muốn xóa không?',
      text: 'Dữ liệu sẽ không thể phục hồi!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Đồng ý xóa!'
    }).then((result) => {
      if (result.value) {
        this.categoryService.delete(id).subscribe(data =>{
          console.log(data);
          this.getListCategory();
        });
        Swal.fire(
          'Đã xóa!',
          'Dữ liệu đã xóa.',
          'success'
        );
      }
    });
    
  }

}
