package com.example.furniturewebdemo1.model;

import com.example.furniturewebdemo1.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "invoiceproduct")
@JsonIgnoreProperties({"invoiceProduct","receipts","invoiceproduct_products"})
public class InvoiceProduct extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoiceproduct_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("invoiceProducts")
    private Employee employee;


    @Column(name = "total")
    private double total;

    @OneToMany(mappedBy = "invoiceProduct", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Invoiceproduct_Product> invoiceproduct_products;

    @OneToMany(mappedBy = "invoiceProduct", cascade = CascadeType.ALL)
    private Collection<Receipt> receipts;

}
