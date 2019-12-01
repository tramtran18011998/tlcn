package com.example.furniturewebdemo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;


    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;



//    @ManyToMany(mappedBy = "roles")
//    @JsonIgnoreProperties("roles")
//    private Set<User> users;
//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("role")
//    private Collection<Employee> employees;

}
