import { Component, OnInit } from '@angular/core';
import { CartService } from '../corecontrol/services/cart.service';
import { User } from '../corecontrol/models/user';
import { Customer } from '../corecontrol/models/customer';
import { CustomerService } from '../corecontrol/services/customer.service';
import { Cart } from '../corecontrol/models/cart';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  currentUser: User = new User();
  currentCustomer: Customer = new Customer();
  carts: Cart[]=[];
  cart: Cart = new Cart();
  
  subtotal: number =0;
  total: number =0;

  cart1: Cart = new Cart();
  idCus: number;

  constructor(private cartService: CartService, private customerService: CustomerService,private router: Router) { }

  ngOnInit() {
    
    this.currentUser = JSON.parse(localStorage.getItem('currentuser'));
    this.getList();
  }

  getList(){
    this.customerService.getIdByUserId(this.currentUser.id).subscribe(data => {
      console.log(data);
      this.idCus = data;
      this.customerService.getById(this.idCus).subscribe(data => {
        this.currentCustomer = data;
        console.log(this.currentCustomer);

        this.cartService.getListCartByCustomer(this.currentCustomer.id).subscribe(data2 => {
          this.carts = data2;
          console.log(this.carts);
          for(let i=0; i< this.carts.length; i++){
            console.log(this.carts[i].totalprice);
            this.subtotal+= this.carts[i].totalprice;
            console.log(this.subtotal);
          }
          this.total = this.subtotal - this.subtotal*this.currentCustomer.discount;
          console.log(this.total);
        })
      })
    })
  }

  edit(id: number){
    this.router.navigate(['/profile',id]);
  }
  // getCustomerByUser(){
  //   this.customerService.getIdByUserId(this.currentUser.id).subscribe(data => {
  //     console.log(data);
  //     this.idCus = data;
  //     this.customerService.getById(this.idCus).subscribe(data => {
  //       this.currentCustomer = data;
  //       console.log(this.currentCustomer);
  //     })
  //   })
  // }

  delete(id: number){
    this.cartService.delete(id).subscribe(data => {
      console.log(data);
      this.getList();
    })
  }

  onChange(valueIN) {
    console.log(valueIN);
  }

  onSearchChange(value: number, id: number){

    this.cartService.getById(id).subscribe(data => {

      
      this.cart1 = data;
      console.log(this.cart1);
      //this.cart1.quantity = value;
      //this.cart1.totalprice = this.cart1.price * value;

      const cart2: Cart = new Cart();
      cart2.id = this.cart1.id;
      cart2.quantity = value;
      cart2.totalprice = this.cart1.price * value;

      this.cartService.update(cart2.id,cart2).subscribe(data2=> {
        console.log(data2);
        this.getList();
      })
    })
    console.log(value);
    console.log(id);
    
  }


}
