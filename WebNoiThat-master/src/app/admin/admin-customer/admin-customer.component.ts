import { Component, OnInit,ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatSort } from '@angular/material';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin-customer',
  templateUrl: './admin-customer.component.html',
  styleUrls: ['./admin-customer.component.css']
})
export class AdminCustomerComponent implements OnInit {

  displayedColumns: string[] = ['name', 'address', 'email','phoneNumber','provider','choose'];

  dataSource ;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  //private customer: User = new Us

  constructor() { }

  ngOnInit() {
    
  }

  getList(){
    // this.supplierService.getList().subscribe(data =>{
    //   console.log(data);
    //   this.suppliers = data;
    //   console.log("sup: " +this.suppliers);
    //   this.dataSource = new MatTableDataSource(this.suppliers);
    //   this.dataSource.paginator = this.paginator;
    //   this.dataSource.sort = this.sort;
    // })
    
  }
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
