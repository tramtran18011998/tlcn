package com.example.furniturewebdemo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart")
@JsonIgnoreProperties({"products"})
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private long id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "cart_product", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonIgnoreProperties("carts")
    private Set<Product> products;

    private String productname;

    private long quantity;

    private double price;

    private double totalprice;

    private int status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("carts")
    private User user;
}
