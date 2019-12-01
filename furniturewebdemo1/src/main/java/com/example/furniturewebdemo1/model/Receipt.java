package com.example.furniturewebdemo1.model;

import com.example.furniturewebdemo1.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "receipt")
@JsonIgnoreProperties({"receipts"})
public class Receipt extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("receipts")
    private Employee employee;

    @Column(name = "total")
    private double total;

    @Column(name = "discount")
    private double discount;

    @Column(name = "amount")
    private double amount;

    @Column(name = "state_delivering")
    private boolean stateDelivering;

    @Column(name = "state_delivered")
    private boolean stateDelivered;

    @Column(name = "state_paid")
    private boolean statePaid;

    @ManyToOne
    @JoinColumn(name = "invoiceproduct_id")
    private InvoiceProduct invoiceProduct;

    @ManyToOne
    @JoinColumn(name = "invoicedetail_id")
    private InvoiceDetail invoiceDetail;

}
