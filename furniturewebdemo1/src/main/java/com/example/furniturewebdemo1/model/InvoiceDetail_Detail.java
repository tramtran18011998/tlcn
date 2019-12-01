package com.example.furniturewebdemo1.model;

import com.example.furniturewebdemo1.model.PrimaryKey.InvoiceDetail_Detail_Id;
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
@Table(name = "invoicedetail_detail")
@JsonIgnoreProperties("invoiceDetail_details")
public class InvoiceDetail_Detail implements Serializable {
    @EmbeddedId
    private InvoiceDetail_Detail_Id id = new InvoiceDetail_Detail_Id();

    private long quantity;

    private double price;

    private double totalprice;

    @ManyToOne
    @MapsId("invoicedetail_id")
    @JoinColumn(name = "invoicedetail_id")
    private InvoiceDetail invoiceDetail;


    @ManyToOne
    @MapsId("detail_id")
    @JoinColumn(name = "detail_id")
    private Detail detail;
}
