import { Component, OnInit } from '@angular/core';
import { SupplierService } from 'src/app/corecontrol/services/supplier.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Supplier } from 'src/app/corecontrol/models/supplier';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-ad-supplier-add',
  templateUrl: './ad-supplier-add.component.html',
  styleUrls: ['./ad-supplier-add.component.css']
})
export class AdSupplierAddComponent implements OnInit {

  constructor(private supplierService: SupplierService, private router: Router, private formBuilder: FormBuilder) { }

  addForm: FormGroup;

  supplier: Supplier = new Supplier();
  ngOnInit() {

    this.addForm = this.formBuilder.group({

      
      name:new FormControl( '', [Validators.required,Validators.pattern('^[a-zA-Z ]+$')]),
      email: new FormControl('',  [Validators.required,Validators.email]),
      phoneNumber: new FormControl('', [Validators.required, Validators.pattern('[0-9]*'), Validators.maxLength(10), Validators.minLength(10)]),
      address: new FormControl('')
    });
  }

  onSubmitCreate(addForm: FormGroup) {
    
    this.supplierService.createNew(this.addForm.value).subscribe(data => {
      console.log(data);
      Swal.fire(
        'Đã thêm!',
        'Dữ liệu đã được thêm thành công.',
        'success'
      );  
    })
    addForm.reset();

  }
}
