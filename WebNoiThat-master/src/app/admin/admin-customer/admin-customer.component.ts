import { Component, OnInit,ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatSort } from '@angular/material';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { User } from 'src/app/corecontrol/models/user';
import { CustomerService } from 'src/app/corecontrol/services/customer.service';

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

  private customers: User[]=[];

  constructor(private customerService: CustomerService, private router: Router) { }

  ngOnInit() {
    this.getList();
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

    this.customerService.getList().subscribe(data =>{
      console.log(data);
      this.customers = data;
      console.log("Customer: "+ this.customers);
      this.dataSource = new MatTableDataSource(this.customers);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
    
  }
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  detail(id: number){    
    this.router.navigate(['/admin/adcustomer/detail',id]);
  }
}
