import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../corecontrol/services/product.service';

@Component({
  selector: 'app-productpage',
  templateUrl: './productpage.component.html',
  styleUrls: ['./productpage.component.css']
})
export class ProductpageComponent implements OnInit {

  id: number;

  constructor(private acroute: ActivatedRoute, private productService:ProductService) { }

  ngOnInit() {
    this.id = this.acroute.snapshot.params['id'];
  }

}
