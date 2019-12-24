package com.example.furniturewebdemo1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "customer")
@JsonIgnoreProperties({"customer","invoiceDetails","invoiceProducts","carts","cartDetails"})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;


    @Column(name = "discount")
    private double discount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("customers")
    private User user;

    @ManyToOne
    @JoinColumn(name = "customertype_id")
    @JsonIgnoreProperties("customers")
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<InvoiceProduct> invoiceProducts;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<InvoiceDetail> invoiceDetails;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("customer")
    private Set<Cart> carts;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("customer")
    private Set<CartDetail> cartDetails;
}
