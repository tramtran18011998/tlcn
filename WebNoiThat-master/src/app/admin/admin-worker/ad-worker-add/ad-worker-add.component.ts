import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import Swal from 'sweetalert2'
import { EmployeeService } from 'src/app/corecontrol/services/employee.service';
import { Customvalidators } from 'src/app/corecontrol/validators/customvalidators';

@Component({
  selector: 'app-ad-worker-add',
  templateUrl: './ad-worker-add.component.html',
  styleUrls: ['./ad-worker-add.component.css']
})
export class AdWorkerAddComponent implements OnInit {


  image: File;

  constructor(private employeeService: EmployeeService, private router: Router, private formBuilder: FormBuilder) { }

  addForm: FormGroup;

  ngOnInit() {
    this.addForm = this.formBuilder.group({     
      name:new FormControl( '', [Validators.required,Validators.pattern('^[a-zA-Z ]+$')]),
      email: new FormControl('',  [Validators.required,Validators.email]),
      phoneNumber: new FormControl('', [Validators.required, Validators.pattern('[0-9]*'), Validators.maxLength(10), Validators.minLength(10)]),
      address: new FormControl(''),
      password: new FormControl('',[Validators.required,Validators.minLength(6),Validators.pattern('^[a-zA-Z0-9_.-]{6,20}$')]),
      password2: new FormControl('',Validators.required),
      createdDate: new FormControl(''),
      file: new FormControl(''),
      instatus: new FormControl(''),
      position: new FormControl(''),
      salary: new FormControl(0),
      bonus: new FormControl(0)
    },{validators: Customvalidators.passwordMatchValidator});
  }


  selectImage(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      //this.images = file;
      this.addForm.controls['file'].setValue(file);
    }


  }
  onSubmitCreate(addForm1: FormGroup) {    
    this.addForm.controls['instatus'].setValue(1);
    const formData = new FormData();
    formData.append('name', this.addForm.get('name').value);
    formData.append('email', this.addForm.get('email').value);
    formData.append('phoneNumber', this.addForm.get('phoneNumber').value);
    formData.append('address', this.addForm.get('address').value);
    formData.append('password', this.addForm.get('password').value);
    formData.append('file', this.addForm.get('file').value);
    formData.append('instatus', this.addForm.get('instatus').value);
    formData.append('position', this.addForm.get('position').value);
    formData.append('salary', this.addForm.get('salary').value);
    formData.append('bonus', this.addForm.get('bonus').value);



    this.employeeService.createNew(formData).subscribe(data => {
      console.log(data);
      Swal.fire(
        'Đã thêm!',
        'Dữ liệu đã được thêm thành công.',
        'success'
      );  
    })
    addForm1.reset();

  }

}
