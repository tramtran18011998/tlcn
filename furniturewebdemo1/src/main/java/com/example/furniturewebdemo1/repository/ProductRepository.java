package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.Product;
import com.example.furniturewebdemo1.model.ProductImage;
import com.example.furniturewebdemo1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

//    @Query(value = "select *from product_image as pi where pi.product_id=:product_id",nativeQuery = true)
//    List<ProductImage> listProductImageByProductId(@Param("product_id") long product_id);

    @Query(value = "select p.product_id,p.color, p.description, p.discount_price, p.material, p.name, p.price, p.quantity, p.size, p.category_id, p.supplier_id \n" +
            "from ((category as c inner join product as p on c.category_id = p.category_id) \n" +
            "inner join category_categorytype as cc on c.category_id = cc.category_id)\n" +
            "inner join categorytype as ct on cc.categorytype_id = ct.categorytype_id\n" +
            "where ct.categorytype_id =:categorytype_id",nativeQuery = true)
    List<Product> listProductCategoryType(@Param("categorytype_id") long categorytype_id);

    @Query(value = "SELECT COUNT(product_id) FROM product",nativeQuery = true)
    long total();

    @Query(value = "SELECT * FROM product\n" +
            "ORDER BY discount_price desc",nativeQuery = true)
    List<Product> sortpricedesc();

    @Query(value = "SELECT * FROM product\n" +
            "ORDER BY discount_price asc",nativeQuery = true)
    List<Product> sortpriceasc();
}
