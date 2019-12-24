import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../corecontrol/services/product.service';
import { Product } from '../corecontrol/models/product';
import { ProductImage } from '../corecontrol/models/productimage';
import { CartService } from '../corecontrol/services/cart.service';
import { Cart } from '../corecontrol/models/cart';
import { Customer } from '../corecontrol/models/customer';
import { User } from '../corecontrol/models/user';
import { CustomerService } from '../corecontrol/services/customer.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-productpage',
  templateUrl: './productpage.component.html',
  styleUrls: ['./productpage.component.css']
})
export class ProductpageComponent implements OnInit {

  product: Product = new Product();
  productImgs: ProductImage[] = [];
  currentCustomer: Customer = new Customer();
  currentUser: User = new User();
  idCus: number;

  cart: Cart = new Cart();

  id: number;
  idImg: number;
  currentImg: string;
  quantity: number = 1;

  constructor(private acroute: ActivatedRoute, private productService: ProductService, private cartService: CartService, private customerService: CustomerService) { }

  ngOnInit() {
    this.id = this.acroute.snapshot.params['id'];

    this.currentUser = JSON.parse(localStorage.getItem('currentuser'));

    this.productService.getById(this.id).subscribe(data => {
      this.product = data;
      console.log(this.product);
    }, error => console.log(error));

    this.productService.getProductImgByProductId(this.id).subscribe(data => {
      this.productImgs = data;
      console.log(this.productImgs);
      //console.log(this.productImgs.na)
      this.currentImg = this.productImgs[0].name;
      // for(let i=0; i<this.productImgs.length;i++){
      //   this.currentImg = this.productImgs[0].name;
      // }
    }, error => console.log(error));


  }

  ImageClick(value: string) {
    console.log(value);
    this.currentImg = value;
  }

  onSearchChange(value: number) {
    this.quantity = value;
    console.log(this.quantity);
  }

  addCart() {

    if (!this.currentUser) {
      Swal.fire(
        'Bạn chưa đăng nhập!',
        'Bạn cần đăng nhập để thực hiện mua hàng.',
        'success'
      );
    } else {
      this.customerService.getIdByUserId(this.currentUser.id).subscribe(data => {
        console.log(data);
        this.idCus = data;
        this.customerService.getById(this.idCus).subscribe(data => {
          this.currentCustomer = data;
          console.log(this.currentCustomer);

          //this.cart.product = this.product;
          this.cart.productname = this.product.name;
          this.cart.price = this.product.discountPrice;
          this.cart.quantity = this.quantity;
          this.cart.status = 0;
          this.cart.totalprice = this.quantity * this.product.discountPrice;
          //this.cart.customer = this.currentCustomer;
          console.log(this.cart);
          this.cartService.createNew(this.product.id,this.currentCustomer.id,this.cart).subscribe(data => {
            console.log(data);
          })

        })
      })
    }


  }

}
