import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from 'src/app/corecontrol/services/employee.service';
import { Employee } from 'src/app/corecontrol/models/employee';
import { User } from 'src/app/corecontrol/models/user';
import Swal from 'sweetalert2'


@Component({
  selector: 'app-ad-worker-edit',
  templateUrl: './ad-worker-edit.component.html',
  styleUrls: ['./ad-worker-edit.component.css']
})
export class AdWorkerEditComponent implements OnInit {

  id: number;
  id2: number;
  employeeUser: User = new User();
  employee: Employee = new Employee();

  instatus = 0;

  constructor(private acroute: ActivatedRoute, private employeeService: EmployeeService) { }

  ngOnInit() {
    this.id= this.acroute.snapshot.params['id'];

    //get user by id
    this.employeeService.getUserById(this.id).subscribe(data=>{
      console.log(data)
      this.employeeUser=data;
      this.instatus = this.employeeUser.instatus;
    },error=>console.log(error));


    //get id employee by user id
    this.employeeService.getIdByUserId(this.id).subscribe(data=>{
      console.log(data)
      this.id2=data;

      //get employee by id
      this.employeeService.getById(this.id2).subscribe(data=>{
        console.log(data)
        this.employee=data;
      },error=>console.log(error));

    },error=>console.log(error));

    
  }
  onSubmit(){
    this.employeeService.update(this.id2, this.employee).subscribe(data2 => {
      console.log("e: "+data2);
    });
    
    this.employeeService.updateUser(this.id,this.employeeUser).subscribe(data=>{
      console.log(data);
      
         
    },error=>console.log(error));

    
    Swal.fire(
      'Đã cập nhật!',
      'Dữ liệu đã được cập nhật.',
      'success'
    );   
  }

}
