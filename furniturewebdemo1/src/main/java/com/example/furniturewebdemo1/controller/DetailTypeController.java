package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;
import com.example.furniturewebdemo1.model.DetailType;
import com.example.furniturewebdemo1.service.DetailTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DetailTypeController {
    @Autowired
    private DetailTypeService detailTypeService;

    @GetMapping("/detailtype")
    public List<DetailType> getAllDetailType(){
        return detailTypeService.findAllDetailTypes();
    }

    @GetMapping("/detailtype/{id}")
    public ResponseEntity<DetailType> getDetailTypeById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        DetailType detailType=detailTypeService.findDetailTypeById(id).orElseThrow(()-> new ResourceNotFoundException("DetailType not found"));
        return ResponseEntity.ok().body(detailType);
    }

    @PostMapping("/detailtype")
    public  ResponseEntity<DetailType> createDetailType(@Valid @RequestBody DetailType detailType){
        detailTypeService.save(detailType);
        return new ResponseEntity<>(detailType, HttpStatus.CREATED);
    }

    @PutMapping("/detailtype/{id}")
    public ResponseEntity<DetailType> updateDetailType(@PathVariable(value = "id") long id, @Valid @RequestBody DetailType detailType) throws ResourceNotFoundException {
        DetailType currentDetailType= detailTypeService.findDetailTypeById(id).orElseThrow(()-> new ResourceNotFoundException("DetailType not found"));

        currentDetailType.setName(detailType.getName());

        detailTypeService.save(currentDetailType);
        return ResponseEntity.ok(currentDetailType);

    }

    @DeleteMapping("/detailtype/{id}")
    public ResponseEntity<DetailType> deleteDetailType(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        DetailType detailType=detailTypeService.findDetailTypeById(id).orElseThrow(()-> new ResourceNotFoundException("DetailType not found"));
        detailTypeService.delete(detailType);
        return ResponseEntity.ok(detailType);
    }
}
