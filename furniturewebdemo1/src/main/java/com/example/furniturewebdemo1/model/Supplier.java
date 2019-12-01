package com.example.furniturewebdemo1.model;

import com.example.furniturewebdemo1.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("supplier")
    private Collection<Product> products;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("supplier")
    private Collection<Detail> details;
}
