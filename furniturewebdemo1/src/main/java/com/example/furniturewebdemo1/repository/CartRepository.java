package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {


    // alway check status = 0
    @Query(value = "select *from cart c where c.status='0' and c.product_id =:product_id and c.customer_id =:customer_id",nativeQuery = true)
    Cart checkCartExistProCus(@Param("product_id") long product_id,@Param("customer_id") long customer_id);

    @Query(value = "SELECT COUNT(cart_id) FROM cart c where c.status='0' and c.customer_id =:customer_id",nativeQuery = true)
    long countQuantity(@Param("customer_id") long customer_id);

    @Query(value = "select *from cart c where c.status='0' and c.customer_id =:customer_id",nativeQuery = true)
    List<Cart> getListCartByCustomer(@Param("customer_id") long customer_id);
}
