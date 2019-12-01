package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.Detail;
import com.example.furniturewebdemo1.repository.DetailRepository;
import com.example.furniturewebdemo1.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailServiceImpl implements DetailService {

    DetailRepository detailRepository;

    @Autowired
    public DetailServiceImpl(DetailRepository detailRepository){
        this.detailRepository=detailRepository;
    }

    @Override
    public List<Detail> findAllDetail() {
        return detailRepository.findAll();
    }

    @Override
    public Optional<Detail> findDetailById(long id) {
        return detailRepository.findById(id);
    }

    @Override
    public void save(Detail detail) {
        detailRepository.save(detail);
    }

    @Override
    public void delete(Detail detail) {
        detailRepository.delete(detail);
    }
}
