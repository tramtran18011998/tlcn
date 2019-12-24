package com.example.furniturewebdemo1.service;

import com.example.furniturewebdemo1.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<Cart> findAllCart();
    Optional<Cart> findCartById(long id);
    void save (Cart cart);
    void delete (Cart cart);
}
