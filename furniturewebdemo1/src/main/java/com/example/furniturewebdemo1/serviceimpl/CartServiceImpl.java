package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.Cart;
import com.example.furniturewebdemo1.repository.CartRepository;
import com.example.furniturewebdemo1.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    CartRepository cartRepository;

    @Autowired
    public CartServiceImpl (CartRepository cartRepository){
        this.cartRepository=cartRepository;
    }
    @Override
    public List<Cart> findAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findCartById(long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);

    }

    @Override
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }
}
