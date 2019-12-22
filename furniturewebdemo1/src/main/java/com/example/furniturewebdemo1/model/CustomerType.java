package com.example.furniturewebdemo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "customertype")
public class CustomerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customertype_id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customerType", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("customerType")
    private Set<Customer> customers;

}
