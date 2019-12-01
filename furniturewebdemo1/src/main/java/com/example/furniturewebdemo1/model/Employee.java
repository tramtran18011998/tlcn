package com.example.furniturewebdemo1.model;

import com.example.furniturewebdemo1.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employee")
@JsonIgnoreProperties({"invoiceDetails", "employee", "invoiceProducts", "receipts","employees"})
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private long id;


    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private double salary;

    @Column(name = "bonus")
    private double bonus;

    //    @ManyToOne
//    @JoinColumn(name = "role_id")
//    @JsonIgnoreProperties("employees")
//    private Role role;


    @ManyToOne
    @JoinColumn(name = "email")
    @JsonIgnoreProperties("employees")
    private User user;


    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<InvoiceProduct> invoiceProducts;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<InvoiceDetail> invoiceDetails;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Receipt> receipts;


}
