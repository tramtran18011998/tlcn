import { Component, OnInit, ViewChild } from '@angular/core';
import { Supplier } from 'src/app/corecontrol/models/supplier';
import { SupplierService } from 'src/app/corecontrol/services/supplier.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatSort } from '@angular/material';

@Component({
  selector: 'app-admin-supplier',
  templateUrl: './admin-supplier.component.html',
  styleUrls: ['./admin-supplier.component.css']
})
export class AdminSupplierComponent implements OnInit {

  displayedColumns: string[] = ['id','name', 'address', 'email','phoneNumber'];

  dataSource ;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  private suppliers: Supplier[]=[];
  
  constructor(private supplierService: SupplierService) { }

  ngOnInit() {
    this.getList();
    //this.datasource = this.suppliers;
    //console.log("sub2: "+ this.suppliers);
    //DataSource;
  }

  getList(){
    this.supplierService.getList().subscribe(data =>{
      console.log(data);
      this.suppliers = data;
      console.log("sup: " +this.suppliers);
      this.dataSource = new MatTableDataSource(this.suppliers);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
    
  }
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
