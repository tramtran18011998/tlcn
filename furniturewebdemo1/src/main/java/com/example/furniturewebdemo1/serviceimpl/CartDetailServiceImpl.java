package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.CartDetail;
import com.example.furniturewebdemo1.repository.CartDetailRepository;
import com.example.furniturewebdemo1.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    CartDetailRepository cartDetailRepository;

    @Autowired
    public CartDetailServiceImpl (CartDetailRepository cartDetailRepository){
        this.cartDetailRepository=cartDetailRepository;
    }

    @Override
    public List<CartDetail> findAllCartDetail() {
        return cartDetailRepository.findAll();
    }

    @Override
    public Optional<CartDetail> findCartDetailById(long id) {
        return cartDetailRepository.findById(id);
    }

    @Override
    public void save(CartDetail cartDetail) {
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public void delete(CartDetail cartDetail) {

        cartDetailRepository.delete(cartDetail);
    }
}
