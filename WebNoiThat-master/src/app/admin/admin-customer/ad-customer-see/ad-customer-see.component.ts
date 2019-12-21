import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/corecontrol/models/user';
import { ActivatedRoute } from '@angular/router';
import { Customer } from 'src/app/corecontrol/models/customer';
import { CustomerService } from 'src/app/corecontrol/services/customer.service';
import { CustomerType } from 'src/app/corecontrol/models/customertype';

@Component({
  selector: 'app-ad-customer-see',
  templateUrl: './ad-customer-see.component.html',
  styleUrls: ['./ad-customer-see.component.css']
})
export class AdCustomerSeeComponent implements OnInit {

  customer: User = new User();
  customer2: Customer = new Customer();
  provider: string;
  id: number;
  id2: number;

  instatus = 0;
  customerType: CustomerType = new CustomerType();

  constructor(private acroute: ActivatedRoute, private customerService: CustomerService) { }

  ngOnInit() {
    this.id = this.acroute.snapshot.params['id'];

    this.customerService.getUserById(this.id).subscribe(data=>{
      console.log(data)
      this.customer=data;
      this.provider= this.customer.provider;
      console.log("provider"+this.provider);
      this.instatus = this.customer.instatus;
    },error=>console.log(error));

    this.customerService.getIdByUserId(this.id).subscribe(data=>{
      console.log(data)
      this.id2=data;
      this.customerService.getById(this.id2).subscribe(data=>{
        console.log(data)
        this.customer2 = data;
        this.customerType = this.customer2.customerType;
      },error=>console.log(error));
    },error=>console.log(error));

  }

}
