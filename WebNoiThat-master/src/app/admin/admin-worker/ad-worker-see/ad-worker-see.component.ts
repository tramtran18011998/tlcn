import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/corecontrol/models/user';
import { ActivatedRoute } from '@angular/router';
import { Employee } from 'src/app/corecontrol/models/employee';
import { EmployeeService } from 'src/app/corecontrol/services/employee.service';

@Component({
  selector: 'app-ad-worker-see',
  templateUrl: './ad-worker-see.component.html',
  styleUrls: ['./ad-worker-see.component.css']
})
export class AdWorkerSeeComponent implements OnInit {

  employeeUser: User = new User();
  employee: Employee = new Employee();
  
  id: number;
  id2: number;

  instatus = 0;

  constructor(private acroute: ActivatedRoute, private employeeService: EmployeeService) { }

  ngOnInit() {
    this.id = this.acroute.snapshot.params['id'];

    this.employeeService.getUserById(this.id).subscribe(data=>{
      console.log(data)
      this.employeeUser=data;
      this.instatus = this.employeeUser.instatus;
    },error=>console.log(error));

    this.employeeService.getIdByUserId(this.id).subscribe(data=>{
      console.log(data)
      this.id2=data;
      this.employeeService.getById(this.id2).subscribe(data=>{
        console.log(data)
        this.employee = data;
      },error=>console.log(error));
    },error=>console.log(error));

  }

}
