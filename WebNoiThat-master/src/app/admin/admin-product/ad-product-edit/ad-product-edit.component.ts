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

  catename: string;
  supname: string;

  constructor(private acroute: ActivatedRoute, private productService: ProductService,private supplierService: SupplierService, private categoryService: CategoryService) { }

  ngOnInit() {
    this.id = this.acroute.snapshot.params['id'];
    this.getCategoryList();
    this.getSupplierList();

    this.productService.getById(this.id).subscribe(data => {
      this.product = data;
      console.log(this.product);
      console.log('b:',this.product.supplier);

      this.supplierService.getById(this.product.supplier.id).subscribe(data => {
        console.log('a',data);
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

}
