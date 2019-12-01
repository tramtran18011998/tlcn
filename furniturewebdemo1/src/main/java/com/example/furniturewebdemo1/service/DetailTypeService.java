package com.example.furniturewebdemo1.service;


import com.example.furniturewebdemo1.model.DetailType;

import java.util.List;
import java.util.Optional;

public interface DetailTypeService {
    List<DetailType> findAllDetailTypes();
    Optional<DetailType> findDetailTypeById(long id);
    void save (DetailType detailType);
    void delete (DetailType detailType);
}
