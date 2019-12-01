package com.example.furniturewebdemo1.model;

import com.example.furniturewebdemo1.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "invoicedetail")
@JsonIgnoreProperties({"invoiceDetail","receipts"})
public class InvoiceDetail extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoicedetail_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("invoiceDetails")
    private Employee employee;


    @Column(name = "total")
    private double total;

    @OneToMany(mappedBy = "invoiceDetail", cascade = CascadeType.ALL)
    private Collection<Receipt> receipts;

}
