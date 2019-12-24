package com.example.furniturewebdemo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cartdetail")
@JsonIgnoreProperties({"products"})
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartdetail_id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detail_id")
    @JsonIgnoreProperties("cartDetails")
    private Detail detail;

    private String detailname;

    private long quantity;

    private double price;

    private double totalprice;

    private int status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("cartDetails")
    private Customer customer;
}
