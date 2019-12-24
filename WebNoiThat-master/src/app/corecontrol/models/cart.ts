import { Product } from './product';
import { Customer } from './customer';

export  class Cart {
    id: number;
    product: Product;
    productname: string;
    quantity: number;
    price: number;
    totalprice: number;
    status: number;
    customer: Customer;
}