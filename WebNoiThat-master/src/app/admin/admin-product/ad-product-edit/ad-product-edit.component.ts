import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/corecontrol/services/product.service';
import { Product } from 'src/app/corecontrol/models/product';
import { ProductImage } from 'src/app/corecontrol/models/productimage';
import { SupplierService } from 'src/app/corecontrol/services/supplier.service';
import { CategoryService } from 'src/app/corecontrol/services/category.service';
import { Supplier } from 'src/app/corecontrol/models/supplier';
import { Category } from 'src/app/corecontrol/models/category';

@Component({
  selector: 'app-ad-product-edit',
  templateUrl: './ad-product-edit.component.html',
  styleUrls: ['./ad-product-edit.component.css']
})
export class AdProductEditComponent implements OnInit {

  id: number;
  product: Product= new Product();
  supplier: Supplier = new Supplier();
  category: Category = new Category();


  productImgs: ProductImage[] =[];
  suppliers: Supplier[]=[];
  categories: Category[]=[];

  urls = [];

  constructor(private acroute: ActivatedRoute, private productService: ProductService,private supplierService: SupplierService, private categoryService: CategoryService) { }

  ngOnInit() {
    this.id = this.acroute.snapshot.params['id'];
    this.getCategoryList();
    this.getSupplierList();

    this.productService.getById(this.id).subscribe(data => {
      this.product = data;
      console.log(this.product);
      console.log('b:',this.product.supplier);

      this.supplier = this.product.supplier;
      console.log(this.supplier);
      this.category = this.product.category;
      console.log(this.category);
      
      this.supplierService.getById(this.product.supplier.id).subscribe(data => {
        console.log('a',data);
        
      })

      this.categoryService.getById(this.product.category.id).subscribe(data => {
        console.log('c:',data);
      })
      
    },error=>console.log(error));

    this.productService.getProductImgByProductId(this.id).subscribe(data => {
      this.productImgs = data;
      console.log(this.productImgs);
      //console.log(this.productImgs.na)
    },error=>console.log(error));

    
  }

  getSupplierList() {
    this.supplierService.getList().subscribe(data => {
      this.suppliers = data;
      console.log(this.suppliers);
    })
  }

  getCategoryList() {
    this.categoryService.getList().subscribe(data => {
      this.categories = data;
      console.log(this.categories);
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
    this.categoryService.getById(value).subscribe(data=>{
      console.log(data);
      this.category = data;
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
    
    this.product.category=this.category;
    this.product.supplier= this.supplier;

    console.log(this.product.category);
    console.log(this.product.supplier);

    this.productService.update(this.id,this.product).subscribe(data=>{
      console.log(data);
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
  }

}
