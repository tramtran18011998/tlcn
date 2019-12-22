package com.example.furniturewebdemo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categorytype")
public class CategoryType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorytype_id")
    private long id;

    @Column(name = "name")
    private String name;


    @ManyToMany(mappedBy = "categoryTypes",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("categoryTypes")
    private Set<Category> categories;

}

