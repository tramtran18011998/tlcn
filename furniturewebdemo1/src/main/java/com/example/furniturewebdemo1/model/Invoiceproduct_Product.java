package com.example.furniturewebdemo1.model;

import com.example.furniturewebdemo1.model.PrimaryKey.Invoiceproduct_Product_Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "invoiceproduct_product")
@JsonIgnoreProperties("invoiceproduct_products")


public class Invoiceproduct_Product implements Serializable {
    @EmbeddedId
    private Invoiceproduct_Product_Id id = new Invoiceproduct_Product_Id();

    private long quantity;

    private double price;

    private double totalprice;

    @ManyToOne
    @MapsId("invoiceproduct_id")
    @JoinColumn(name = "invoiceproduct_id")
    private InvoiceProduct invoiceProduct;


    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;
}
