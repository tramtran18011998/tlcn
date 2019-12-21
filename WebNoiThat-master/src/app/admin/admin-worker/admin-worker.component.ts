import { Component, OnInit,ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatSort } from '@angular/material';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { User } from 'src/app/corecontrol/models/user';
import { EmployeeService } from 'src/app/corecontrol/services/employee.service';

@Component({
  selector: 'app-admin-worker',
  templateUrl: './admin-worker.component.html',
  styleUrls: ['./admin-worker.component.css']
})
export class AdminWorkerComponent implements OnInit {

  displayedColumns: string[] = ['name', 'address', 'email','phoneNumber','provider','choose'];

  dataSource ;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  private employees: User[]=[];
  cus: User = new User();

  constructor(private employeeService: EmployeeService, private router: Router) { }

  ngOnInit() {
    this.getList();
  }

  getList(){

    this.employeeService.getList().subscribe(data =>{
      console.log(data);
      this.employees = data;
      console.log("Employees: "+ this.employees);
      this.dataSource = new MatTableDataSource(this.employees);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
    
  }
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  detail(id: number){    
    this.router.navigate(['/admin/adworker/detail',id]);
  }
  
  edit(id: number){
    this.router.navigate(['/admin/adworker/edit',id]);
  }

  delete(id: number){

    
    this.cus.instatus = 0; 
    

    Swal.fire({
      title: 'Bạn có chắc chắn muốn xóa không?',
      text: 'Trạng thái hoạt động sẽ khóa!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Đồng ý xóa!'
    }).then((result) => {
      if (result.value) {
        this.employeeService.updateUser(id,this.cus).subscribe(data => {
          console.log(data);
          this.getList();
        });
        Swal.fire(
          'Đã xóa!',
          'Đã khóa trạng thái.',
          'success'
        );
      }
    });
  }
}
