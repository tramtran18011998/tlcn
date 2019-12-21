import { Component, OnInit,ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatSort } from '@angular/material';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { User } from 'src/app/corecontrol/models/user';
import { CustomerService } from 'src/app/corecontrol/services/customer.service';
import { CustomerTypeService } from 'src/app/corecontrol/services/customer-type.service';
import { CustomerType } from 'src/app/corecontrol/models/customertype';
import { Customer } from 'src/app/corecontrol/models/customer';

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
  private customerTypes: CustomerType[]=[];
  private customerC: Customer= new Customer();
  private idCus: number;
  customerType: CustomerType = new CustomerType();
  customerTypeA: CustomerType = new CustomerType();


  constructor(private customerService: CustomerService, private router: Router, private customerTypeService: CustomerTypeService) { }

  ngOnInit() {
    this.getList();
    this.getDateCustomerType();
  }

  getList(){
    this.customerService.getList().subscribe(data =>{
      console.log(data);
      this.customers = data;
      console.log("Customer: "+ this.customers);
      this.dataSource = new MatTableDataSource(this.customers);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
    
  }

  getDateCustomerType(){
    this.customerTypeService.getList().subscribe(data => {
      console.log(data);
      this.customerTypes = data;
    })
  }


  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  detail(id: number){    
    this.router.navigate(['/admin/adcustomer/detail',id]);
  }

  edit(id: number){
    this.customerService.getIdByUserId(id).subscribe(data =>{
      this.idCus = data;
      console.log(this.idCus);
      this.customerService.getById(this.idCus).subscribe(data =>{
        this.customerC = data;
        this.customerTypeA = this.customerC.customerType
        console.log("cus: ", this.customerC.customerType);

        //get customertype ob by name
        // this.customerTypeService.getByName(this.customerTypeA.name).subscribe(data =>{
          
        //   this.customerC.customerType = data;
        //   console.log("data: ",this.customerC.customerType);
          
        // })
      });
    });
    
  }
  onSubmitEdit(){
    
    
    console.log(this.customerC.customerType);
    
    this.customerTypeService.getByName(String(this.customerC.customerType) ).subscribe(data =>{
      console.log(data);
      this.customerC.customerType = data;
      this.customerService.update(this.idCus,this.customerC).subscribe(data =>{     
        console.log("data: "+ data);
      });
    })

    // console.log(this.customerC.customerType);
    // console.log("data2: ",this.customerC.customerType);
    
    // this.customerService.update(this.idCus,this.customerC).subscribe(data =>{     
    //   console.log("data3: ", data);
    // });
  }
}
