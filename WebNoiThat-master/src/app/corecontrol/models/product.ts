import { Category } from './category';
import { Supplier } from './supplier';

export  class Product {
    id: number;
    name: string;
    color: string;
    description: string;
    discountPrice: number;
    material: string;
    price: number;
    quantity: number;
    size: string;
    category: Category;
    supplier: Supplier;
}