import { Component, OnInit } from '@angular/core';

import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ad-product-see',
  templateUrl: './ad-product-see.component.html',
  styleUrls: ['./ad-product-see.component.css']
})
export class AdProductSeeComponent implements OnInit {

  constructor(private acroute: ActivatedRoute) { }

  ngOnInit() {
  }

}
