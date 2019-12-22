import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DetailService } from 'src/app/corecontrol/services/detail.service';
import { Detail } from 'src/app/corecontrol/models/detail';
import { DetailImage } from 'src/app/corecontrol/models/detailimage';

@Component({
  selector: 'app-ad-accessories-see',
  templateUrl: './ad-accessories-see.component.html',
  styleUrls: ['./ad-accessories-see.component.css']
})
export class AdAccessoriesSeeComponent implements OnInit {

  detail: Detail = new Detail();
  detailImgs: DetailImage[]=[];

  id: number;
  idImg: number;

  constructor(private acroute: ActivatedRoute, private detailService: DetailService) { }

  ngOnInit() {
    this.id = this.acroute.snapshot.params['id'];

    this.detailService.getById(this.id).subscribe(data => {
      this.detail = data;
      console.log(this.detail);
    },error=>console.log(error));

    this.detailService.getProductImgByProductId(this.id).subscribe(data => {
      this.detailImgs = data;
      console.log(this.detailImgs);
      //console.log(this.productImgs.na)
    },error=>console.log(error));
  }

}
