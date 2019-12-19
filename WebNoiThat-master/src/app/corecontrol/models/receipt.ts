export  class Receipt {
    id: number;
    amount: string;
    discount: string;
    stateDelivering: boolean;
    stateDelivered: boolean;
    statePaid: boolean;
    total: number;
    employee_id: number;
    invoicedetail_id: number;
    invoiceproduct_id: number;

    createdDate: Date;
    updatedDate: Date;
    
}