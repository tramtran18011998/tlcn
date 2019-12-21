package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerType,Long> {

    //get customertype object by name
    @Query(value = "select *from customertype as c where c.name=:name",nativeQuery = true)
    CustomerType getCustomerTypeByName(@Param("name") String name);
}
