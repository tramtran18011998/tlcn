package com.example.furniturewebdemo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "detail")
@JsonIgnoreProperties({"invoiceDetail_details","details"})
public class Detail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "discount_price")
    private double discountPrice;

    @Column(name = "size")
    private String size;

    @Column(name = "material")
    private String material;

    @OneToMany(mappedBy = "detail", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("detail")
    private Collection<DetailImage> detailImages;


    @ManyToMany(mappedBy = "details")
    private Set<Product> products;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detailtype_id")
    private DetailType detailType;

    @OneToMany(mappedBy = "detail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<InvoiceDetail_Detail> invoiceDetail_details;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
