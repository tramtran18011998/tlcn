package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.DetailType;
import com.example.furniturewebdemo1.repository.DetailTypeRepository;
import com.example.furniturewebdemo1.service.DetailTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailTypeServiceImpl implements DetailTypeService {

    DetailTypeRepository detailTypeRepository;

    @Autowired
    public DetailTypeServiceImpl(DetailTypeRepository detailTypeRepository){
        this.detailTypeRepository=detailTypeRepository;
    }

    @Override
    public List<DetailType> findAllDetailTypes() {
        return detailTypeRepository.findAll();
    }

    @Override
    public Optional<DetailType> findDetailTypeById(long id) {
        return detailTypeRepository.findById(id);
    }

    @Override
    public void save(DetailType detailType) {
        detailTypeRepository.save(detailType);
    }

    @Override
    public void delete(DetailType detailType) {
        detailTypeRepository.delete(detailType);
    }
}
