import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import Swal from 'sweetalert2'
import { Supplier } from 'src/app/corecontrol/models/supplier';
import { SupplierService } from 'src/app/corecontrol/services/supplier.service';
import { Detail } from 'src/app/corecontrol/models/detail';
import { DetailTypeService } from 'src/app/corecontrol/services/detail-type.service';
import { DetailType } from 'src/app/corecontrol/models/detailtype';
import { DetailService } from 'src/app/corecontrol/services/detail.service';

@Component({
  selector: 'app-ad-accessories-add',
  templateUrl: './ad-accessories-add.component.html',
  styleUrls: ['./ad-accessories-add.component.css']
})
export class AdAccessoriesAddComponent implements OnInit {

  image: File;
  addForm: FormGroup;
  imgForm: FormGroup;
  detailType: DetailType = new DetailType();
  supplier: Supplier = new Supplier();
  detail: Detail = new Detail();
  detailA: Detail = new Detail();

  detailTypes: DetailType[] = [];
  suppliers: Supplier[]=[];
  urls = [];

  idType: number;
  idSub: number;


  constructor(private supplierService: SupplierService, private detailTypeService: DetailTypeService,private detailService: DetailService ,private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {
    
    this.getSupplierList();
    this.getdetailTypeList();

    //this.getCategoryListByType(1);


    this.addForm = this.formBuilder.group({
      name: new FormControl('', Validators.required),
      price: new FormControl('', [Validators.required, Validators.minLength(10000)]),
      color: new FormControl(''),
      description: new FormControl(''),
      discountPrice: new FormControl(''),
      material: new FormControl(''),
      quantity: new FormControl(0),
      size: new FormControl(''),
      detailType: new FormControl(''),
      supplier: new FormControl('')
    });

  }
  getSupplierList() {
    this.supplierService.getList().subscribe(data => {
      this.suppliers = data;
      console.log(this.suppliers);
    })
  }

  getdetailTypeList() {
    this.detailTypeService.getList().subscribe(data => {
      this.detailTypes = data;
      console.log(this.detailTypes);
    })
  }

  


  onOptionsSelectedCate(value: number) {
    console.log("the selected value is " + value);
    this.idType= value;
  }

  onOptionsSelectedSup(value: number) {
    console.log("the selected value is " + value);
    //this.getCategoryListByType(value);
    this.idSub = value;
  }


  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
        var filesAmount = event.target.files.length;
        for (let i = 0; i < filesAmount; i++) {
          const file = event.target.files[i];
          this.urls.push(file);
          console.log(this.urls);
        }
    }
    console.log("url: ",this.urls);
  }



  onSubmitCreate(addForm: FormGroup){
    
    this.detailA.name = addForm.controls['name'].value;
    this.detailA.price = addForm.controls['price'].value;
    this.detailA.color = addForm.controls['color'].value;
    this.detailA.description = addForm.controls['description'].value;
    this.detailA.discountPrice = addForm.controls['discountPrice'].value;
    this.detailA.material = addForm.controls['material'].value;
    this.detailA.quantity = addForm.controls['quantity'].value;
    this.detailA.size = addForm.controls['size'].value;


    this.supplierService.getById(this.idSub).subscribe(data =>{
      this.supplier = data;
      this.detailA.supplier = this.supplier;

      this.detailTypeService.getById(this.idType).subscribe(data => {
        this.detailA.detailType = data;

        this.detailService.createNew(this.detailA).subscribe(data => {
          console.log("pro:",data);
          this.detail = data;
          console.log(this.detail);
          console.log(this.detail.id);
          if(this.urls.length>0){
            
            for (let i = 0; i < this.urls.length; i++) {
              const formData = new FormData();
              formData.append('file', this.urls[i]);
              console.log(this.urls[i]);
              this.detailService.createProductImg2(this.detail.id,formData).subscribe(data => {
                console.log(data);
              })
            }
            Swal.fire(
              'Đã thêm!',
              'Dữ liệu đã được thêm thành công.',
              'success'
            );  
            
          }
          this.router.navigate(['/admin/addetail']);
        })
      });

    });
    addForm.reset();
 
  }

}
