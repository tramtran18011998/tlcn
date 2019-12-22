import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Detail } from 'src/app/corecontrol/models/detail';
import { Supplier } from 'src/app/corecontrol/models/supplier';
import { DetailType } from 'src/app/corecontrol/models/detailtype';
import { DetailImage } from 'src/app/corecontrol/models/detailimage';
import { DetailService } from 'src/app/corecontrol/services/detail.service';
import { SupplierService } from 'src/app/corecontrol/services/supplier.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-ad-accessories-edit',
  templateUrl: './ad-accessories-edit.component.html',
  styleUrls: ['./ad-accessories-edit.component.css']
})
export class AdAccessoriesEditComponent implements OnInit {

  id: number;
  detail: Detail= new Detail();
  supplier: Supplier = new Supplier();
  detailType: DetailType = new DetailType();
  


  detailImgs: DetailImage[] =[];
  suppliers: Supplier[]=[];
  detailTypes: DetailType[]=[];

  urls = [];
  
  constructor(private acroute: ActivatedRoute, private detailService: DetailService,private supplierService: SupplierService, private detailTypeService: DetailService) { }

  ngOnInit() {
    this.id = this.acroute.snapshot.params['id'];
    this.getDetailTypeList();
    this.getSupplierList();

    this.detailService.getById(this.id).subscribe(data => {
      this.detail = data;
      console.log(this.detail);
      console.log('b:',this.detail.supplier);

      this.supplier = this.detail.supplier;
      console.log(this.supplier);
      this.detailType = this.detail.detailType;
      //console.log(this.category);
      
      this.supplierService.getById(this.detail.supplier.id).subscribe(data => {
        console.log('a',data);
        
      })

      this.detailTypeService.getById(this.detail.detailType.id).subscribe(data => {
        console.log('c:',data);
      })
      
    },error=>console.log(error));

    this.detailService.getProductImgByProductId(this.id).subscribe(data => {
      this.detailImgs = data;
      console.log(this.detailImgs);
      //console.log(this.productImgs.na)
    },error=>console.log(error));

    
  }

  getSupplierList() {
    this.supplierService.getList().subscribe(data => {
      this.suppliers = data;
      console.log(this.suppliers);
    })
  }

  getDetailTypeList() {
    this.detailTypeService.getList().subscribe(data => {
      this.detailTypes = data;
      console.log(this.detailTypes);
    })
  }

  onOptionsSelected(value: number) {
    console.log("the selected value is " + value);
    this.supplierService.getById(value).subscribe(data=>{
      console.log(data);
      this.supplier = data;
    })
  }

  onOptionsSelectedCate(value: number){
    console.log("the selected value is " + value);
    this.detailTypeService.getById(value).subscribe(data=>{
      console.log(data);
      this.detailType = data;
    })
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

  onSubmit(){
    
    this.detail.detailType=this.detailType;
    this.detail.supplier= this.supplier;


    this.detailService.update(this.id,this.detail).subscribe(data=>{
      console.log(data);
      if(this.urls.length>0){
            
        //formData.append('files', this.urls);
        for (let i = 0; i < this.urls.length; i++) {
          const formData = new FormData();
          formData.append('file', this.urls[i]);
          console.log(this.urls[i]);
          this.detailService.createProductImg2(this.detail.id,formData).subscribe(data => {
            console.log(data);
          })
        }
          
        
      }
    })
    Swal.fire(
      'Đã cập nhật!',
      'Dữ liệu đã được sửa thành công.',
      'success'
    );
  }

}