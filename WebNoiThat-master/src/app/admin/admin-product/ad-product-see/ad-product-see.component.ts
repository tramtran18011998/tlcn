import { Component, OnInit } from '@angular/core';

import { ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/corecontrol/services/product.service';
import { Product } from 'src/app/corecontrol/models/product';
import { ProductImage } from 'src/app/corecontrol/models/productimage';

@Component({
  selector: 'app-ad-product-see',
  templateUrl: './ad-product-see.component.html',
  styleUrls: ['./ad-product-see.component.css']
})
export class AdProductSeeComponent implements OnInit {


  product: Product = new Product();
  productImgs: ProductImage[]=[];

  id: number;
  idImg: number;

  constructor(private acroute: ActivatedRoute, private productService: ProductService) { }

  ngOnInit() {
    this.id = this.acroute.snapshot.params['id'];

    this.productService.getById(this.id).subscribe(data => {
      this.product = data;
      console.log(this.product);
    },error=>console.log(error));

    this.productService.getProductImgByProductId(this.id).subscribe(data => {
      this.productImgs = data;
      console.log(this.productImgs);
      //console.log(this.productImgs.na)
    },error=>console.log(error));
  }

}
