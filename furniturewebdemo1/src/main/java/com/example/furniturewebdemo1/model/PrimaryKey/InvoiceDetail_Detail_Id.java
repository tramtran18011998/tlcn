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
public class InvoiceDetail_Detail_Id implements Serializable {
    @Column(name = "invoicedetail_id")
    private long invoicedetail_id;

    @Column(name = "detail_id")
    private long detail_id;
}
