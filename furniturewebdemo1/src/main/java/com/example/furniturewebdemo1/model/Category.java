package com.example.furniturewebdemo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("category")
    private Collection<Product> products;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name = "category_categorytype", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "categorytype_id"))
    @JsonIgnoreProperties("categories")
    private Set<CategoryType> categoryTypes;
}


