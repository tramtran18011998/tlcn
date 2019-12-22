import { InvoiceProduct } from './invoiceproduct';
import { InvoiceDetail } from './invoicedetail';

export  class Receipt {
    id: number;
    amount: string;
    discount: string;
    stateDelivering: boolean;
    stateDelivered: boolean;
    statePaid: boolean;
    total: number;
    employee_id: number;
    invoiceDetail: InvoiceDetail;
    invoiceProduct: InvoiceProduct;

    createdDate: Date;
    updatedDate: Date;
    
}