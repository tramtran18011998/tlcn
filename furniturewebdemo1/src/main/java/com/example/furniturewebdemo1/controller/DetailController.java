package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;

import com.example.furniturewebdemo1.model.Detail;
import com.example.furniturewebdemo1.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DetailController {
    @Autowired
    private DetailService detailService;

    @GetMapping("/detail")
    public List<Detail> getAllDetail(){
        return detailService.findAllDetail();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Detail> getDetailById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Detail detail=detailService.findDetailById(id).orElseThrow(()-> new ResourceNotFoundException("Detail not found"));
        return ResponseEntity.ok().body(detail);
    }

    @PostMapping("/detail")
    public  ResponseEntity<Detail> createDetail(@Valid @RequestBody Detail detail){
        detailService.save(detail);
        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    @PutMapping("/detail/{id}")
    public ResponseEntity<Detail> updateDetail(@PathVariable(value = "id") long id, @Valid @RequestBody Detail detail) throws ResourceNotFoundException {
        Detail currentDetail= detailService.findDetailById(id).orElseThrow(()-> new ResourceNotFoundException("Detail not found"));

        currentDetail.setName(detail.getName());
        currentDetail.setColor(detail.getColor());
        currentDetail.setQuantity(detail.getQuantity());
        currentDetail.setPrice(detail.getPrice());
        currentDetail.setDiscountPrice(detail.getDiscountPrice());
        currentDetail.setSize(detail.getSize());
        currentDetail.setMaterial(detail.getMaterial());
        currentDetail.setImage(detail.getImage());
        currentDetail.setDetailType(detail.getDetailType());
        currentDetail.setSupplier(detail.getSupplier());


        detailService.save(currentDetail);
        return ResponseEntity.ok(currentDetail);

    }

    @DeleteMapping("/detail/{id}")
    public ResponseEntity<Detail> deleteDetail(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Detail detail=detailService.findDetailById(id).orElseThrow(()-> new ResourceNotFoundException("Detail not found"));
        detailService.delete(detail);
        return ResponseEntity.ok(detail);
    }
}
