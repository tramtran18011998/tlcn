package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    //@Query(value = "SELECT * FROM employee WHERE username = :username and password = :password",nativeQuery = true)
    @Query(value = "select r.name from (role as r inner join user_roles as ur on r.role_id = ur.role_id) inner join user as u on ur.user_id = u.user_id\n" +
            "where u.email = :email",nativeQuery = true)
    String findByRoleUser(@Param("email") String email);


}
