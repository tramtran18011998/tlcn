package com.example.furniturewebdemo1.service;

import com.example.furniturewebdemo1.model.CartDetail;

import java.util.List;
import java.util.Optional;

public interface CartDetailService {
    List<CartDetail> findAllCartDetail();
    Optional<CartDetail> findCartDetailById(long id);
    void save (CartDetail cartDetail);
    void delete (CartDetail cartDetail);
}
