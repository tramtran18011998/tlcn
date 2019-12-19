import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SupplierService } from 'src/app/corecontrol/services/supplier.service';
import { Supplier } from 'src/app/corecontrol/models/supplier';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-ad-supplier-edit',
  templateUrl: './ad-supplier-edit.component.html',
  styleUrls: ['./ad-supplier-edit.component.css']
})
export class AdSupplierEditComponent implements OnInit {

  id: number;
  supplier: Supplier = new Supplier();
  constructor(private router: Router, private acroute: ActivatedRoute, private supplierService: SupplierService) { }

  ngOnInit() {
    this.id= this.acroute.snapshot.params['id'];

    this.supplierService.getById(this.id).subscribe(data=>{
      console.log(data)
      this.supplier=data;
    },error=>console.log(error));
  }
  
  onSubmit(){
    this.supplierService.update(this.id,this.supplier).subscribe(data=>{
      console.log(data);
      Swal.fire(
        'Đã cập nhật!',
        'Dữ liệu đã được cập nhật.',
        'success'
      );      
    },error=>console.log(error));
  }

}
