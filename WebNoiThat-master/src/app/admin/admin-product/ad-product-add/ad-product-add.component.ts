import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import Swal from 'sweetalert2'
import { ProductService } from 'src/app/corecontrol/services/product.service';
import { Category } from 'src/app/corecontrol/models/category';
import { CategoryType } from 'src/app/corecontrol/models/categorytype';
import { CategoryTypeService } from 'src/app/corecontrol/services/category-type.service';
import { CategoryService } from 'src/app/corecontrol/services/category.service';
import { Supplier } from 'src/app/corecontrol/models/supplier';
import { SupplierService } from 'src/app/corecontrol/services/supplier.service';
import { Product } from 'src/app/corecontrol/models/product';

@Component({
  selector: 'app-ad-product-add',
  templateUrl: './ad-product-add.component.html',
  styleUrls: ['./ad-product-add.component.css']
})
export class AdProductAddComponent implements OnInit {

  image: File;
  addForm: FormGroup;
  imgForm: FormGroup;
  category: Category = new Category();
  categoryType: CategoryType = new CategoryType();
  supplier: Supplier = new Supplier();
  product: Product = new Product();
  productA: Product = new Product();

  categories: Category[] = [];
  categoryTypes: CategoryType[] = [];
  suppliers: Supplier[]=[];
  urls = [];

  cateTForm: FormGroup;

  constructor(private supplierService: SupplierService,private categoryTypeService: CategoryTypeService, private categoryService: CategoryService, private productService: ProductService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.getCategoryTypeList();
    this.getSupplierList();

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
      category: new FormControl(''),
      supplier: new FormControl('')
    });

    this.cateTForm = this.formBuilder.group([

    ]);

    this.imgForm = this.formBuilder.group([

    ])

  }
  getSupplierList() {
    this.supplierService.getList().subscribe(data => {
      this.suppliers = data;
      console.log(this.suppliers);
    })
  }

  getCategoryTypeList() {
    this.categoryTypeService.getList().subscribe(data => {
      this.categoryTypes = data;
      console.log(this.categoryTypes);
    })
  }

  getCategoryListByType(id: number) {

    this.categoryService.getByType(id).subscribe(data => {
      this.categories = data;
      console.log(this.categories);
    })
  }

  onOptionsSelected(value: number) {
    console.log("the selected value is " + value);
    this.getCategoryListByType(value);
  }


  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
        var filesAmount = event.target.files.length;
        for (let i = 0; i < filesAmount; i++) {
          const file = event.target.files[i];
          this.urls.push(file);
          console.log(this.urls);
                // var reader = new FileReader();

                // reader.onload = (event:any) => {
                //   console.log(event.target.result);
                //    this.urls.push(event.target.result); 
                // }

                // reader.readAsDataURL(event.target.files[i]);
        }
    }
    console.log("url: ",this.urls);
  }



  onSubmitCreate(addForm: FormGroup){
    //console.log(addForm.controls['category'].value);
    
    this.productA.name = addForm.controls['name'].value;
    this.productA.price = addForm.controls['price'].value;
    this.productA.color = addForm.controls['color'].value;
    this.productA.description = addForm.controls['description'].value;
    this.productA.discountPrice = addForm.controls['discountPrice'].value;
    this.productA.material = addForm.controls['material'].value;
    this.productA.quantity = addForm.controls['quantity'].value;
    this.productA.size = addForm.controls['size'].value;

    console.log(this.productA.supplier);
    console.log(this.productA.category);

    this.supplierService.getById(addForm.controls['supplier'].value).subscribe(data =>{
      this.supplier = data;
      this.productA.supplier = this.supplier;

      this.categoryService.getById(addForm.controls['category'].value).subscribe(data => {
        this.productA.category = data;

        this.productService.createNew(this.productA).subscribe(data => {
          console.log("pro:",data);
          this.product = data;
          console.log(this.product);
          console.log(this.product.id);
          if(this.urls.length>0){
            
            //formData.append('files', this.urls);
            for (let i = 0; i < this.urls.length; i++) {
              const formData = new FormData();
              formData.append('file', this.urls[i]);
              console.log(this.urls[i]);
              this.productService.createProductImg2(this.product.id,formData).subscribe(data => {
                console.log(data);
              })
            }
            
          }
          
        })
      });

    });

    

    
  }

}
