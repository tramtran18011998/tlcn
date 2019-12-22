import { Supplier } from './supplier';
import { DetailType } from './detailtype';

export  class Detail {
    id: number;
    name: string;
    color: string;
    description: string;
    discountPrice: number;
    material: string;
    price: number;
    quantity: number;
    size: string;
    detailType: DetailType;
    supplier: Supplier;
}