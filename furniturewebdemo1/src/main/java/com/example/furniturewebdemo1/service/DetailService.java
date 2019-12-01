package com.example.furniturewebdemo1.service;

import com.example.furniturewebdemo1.model.Detail;

import java.util.List;
import java.util.Optional;

public interface DetailService {
    List<Detail> findAllDetail();
    Optional<Detail> findDetailById(long id);
    void save (Detail detail);
    void delete (Detail detail);
}
