package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.Role;
import com.example.furniturewebdemo1.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);
}
