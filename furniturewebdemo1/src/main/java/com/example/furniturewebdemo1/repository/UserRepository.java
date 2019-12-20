package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    //@Query(value = "SELECT * FROM employee WHERE username = :username and password = :password",nativeQuery = true)
    @Query(value = "select r.name from (role as r inner join user_roles as ur on r.role_id = ur.role_id) inner join user as u on ur.user_id = u.user_id\n" +
            "where u.email = :email",nativeQuery = true)
    String findByRoleUser(@Param("email") String email);


    //get list customer: role: ROLE_USER
    @Query(value = "select u.user_id,u.name, u.email, u.image_url, u.email_verified, u.password, u.address,u.phone_number, u.instatus,u.provider,u.provider_id, u.created_date, u.updated_date from (role as r inner join user_roles as ur on r.role_id = ur.role_id) inner join user as u on ur.user_id = u.user_id\n" +
            "where r.name = 'ROLE_USER' and u.instatus ='1'",nativeQuery = true)
    List<User> listCustomer();

    //get customer_id from user_id
    @Query(value = "select c.customer_id from user as u inner join customer as c on u.user_id = c.user_id where u.user_id=:user_id",nativeQuery = true)
    long getCustomerId(@Param("user_id") long user_id);

    //get list employee: role: ROLE_EMPLOYEE
    @Query(value = "select u.user_id,u.name, u.email, u.image_url, u.email_verified, u.password, u.address,u.phone_number, u.instatus,u.provider,u.provider_id, u.created_date, u.updated_date from (role as r inner join user_roles as ur on r.role_id = ur.role_id) inner join user as u on ur.user_id = u.user_id\n" +
            "where r.name = 'ROLE_EMPLOYEE' and u.instatus ='1'",nativeQuery = true)
    List<User> listEmployee();

    //get customer_id from user_id
    @Query(value = "select e.employee_id from user as u inner join employee as e on u.user_id = e.user_id where u.user_id=:user_id",nativeQuery = true)
    long getEmployeeId(@Param("user_id") long user_id);

}
