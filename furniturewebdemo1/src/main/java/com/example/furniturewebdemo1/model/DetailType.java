package com.example.furniturewebdemo1.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "detailtype")
@JsonIgnoreProperties({"detailType","details"})
public class DetailType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detailtype_id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "detailType", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("detailType")
    private Collection<Detail> details;
}
