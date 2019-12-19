import { Component, OnInit, ViewChild } from '@angular/core';
import { Supplier } from 'src/app/corecontrol/models/supplier';
import { SupplierService } from 'src/app/corecontrol/services/supplier.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatSort } from '@angular/material';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin-supplier',
  templateUrl: './admin-supplier.component.html',
  styleUrls: ['./admin-supplier.component.css']
})
export class AdminSupplierComponent implements OnInit {

  displayedColumns: string[] = ['id','name', 'address', 'email','phoneNumber','choose'];

  dataSource ;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  private suppliers: Supplier[]=[];
  
  constructor(private supplierService: SupplierService, private router: Router) { }

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

  delete(id: number){
    // this.supplierService.delete(id).subscribe(data =>{
    //   console.log(data);
    //   this.getList();
    // });
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
        this.supplierService.delete(id).subscribe(data =>{
          console.log(data);
          this.getList();
        });
        Swal.fire(
          'Đã xóa!',
          'Dữ liệu đã xóa.',
          'success'
        );
      }
    });
    
  }
  edit(id: number){
    
    this.router.navigate(['/admin/adsupplier/edit',id]);
    //this.router.navigate(['details',id]);
  }

}
