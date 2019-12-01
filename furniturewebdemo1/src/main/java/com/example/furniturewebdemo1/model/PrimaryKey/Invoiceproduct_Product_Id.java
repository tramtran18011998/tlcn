package com.example.furniturewebdemo1.model.PrimaryKey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@Embeddable
public class Invoiceproduct_Product_Id implements Serializable {

    @Column(name = "invoiceproduct_id")
    private long invoiceproduct_id;

    @Column(name = "product_id")
    private long product_id;

}
