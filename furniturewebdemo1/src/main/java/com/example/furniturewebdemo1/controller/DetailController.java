package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;

import com.example.furniturewebdemo1.model.Detail;
import com.example.furniturewebdemo1.model.DetailImage;
import com.example.furniturewebdemo1.repository.DetailImgeRepository;
import com.example.furniturewebdemo1.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DetailController {
    @Autowired
    private DetailService detailService;

    @Autowired
    private DetailImgeRepository detailImgeRepository;

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

        if(detail.getColor()!=null){
            currentDetail.setColor(detail.getColor());
        }
        if(detail.getDescription()!=null){
            currentDetail.setDescription(detail.getDescription());
        }
        if(detail.getDiscountPrice()==0){
            currentDetail.setDiscountPrice(detail.getPrice());
        }
        else {
            currentDetail.setDiscountPrice(detail.getDiscountPrice());
        }

        if(detail.getMaterial()!=null){
            currentDetail.setMaterial(detail.getMaterial());
        }
        if(detail.getName()!=null){
            currentDetail.setName(detail.getName());
        }
        if (detail.getPrice()!=0){
            currentDetail.setPrice(detail.getPrice());
        }

        if(detail.getQuantity()!=0){
            currentDetail.setQuantity(detail.getQuantity());
        }

        if(detail.getSize()!=null){
            currentDetail.setSize(detail.getSize());
        }
        if (detail.getDetailType()!=null){
            currentDetail.setDetailType(detail.getDetailType());
        }
        if(detail.getSupplier()!=null){
            currentDetail.setSupplier(detail.getSupplier());
        }

        detailService.save(currentDetail);
        return ResponseEntity.ok(currentDetail);

    }

    @DeleteMapping("/detail/{id}")
    public ResponseEntity<Detail> deleteDetail(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Detail detail=detailService.findDetailById(id).orElseThrow(()-> new ResourceNotFoundException("Detail not found"));
        detailService.delete(detail);
        return ResponseEntity.ok(detail);
    }

    public String storeImg(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File convertFile = new File("uploads/details/"+fileName);
        convertFile.createNewFile();

        try (FileOutputStream fout = new FileOutputStream(convertFile))
        {
            fout.write(file.getBytes());
        }
        catch (Exception exe)
        {
            exe.printStackTrace();
        }
        return file.getOriginalFilename();
    }

    //////////IMAGE HANDLING
    @PostMapping("/delimage")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException, ResourceNotFoundException {

        return storeImg(file);
    }


    @PostMapping("/deimages")
    public  List<String> uploadFiles(@RequestParam("files") MultipartFile[] files) throws IOException, ResourceNotFoundException {

        List<String> list = Arrays.asList(files).stream().map(file -> {
            try {
                return upload(file);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        return list;
    }

    @PostMapping(value = "/detailimgA/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<DetailImage> createDetailImage(@RequestParam("file") MultipartFile file,@PathVariable(value = "id") long id) throws IOException, ResourceNotFoundException {

        Detail detail = detailService.findDetailById(id).orElseThrow(()-> new ResourceNotFoundException("D not found"));
        //logger.info(product.getName());
        DetailImage detailImage = new DetailImage();
        detailImage.setDetail(detail);
        detailImage.setName(storeImg(file));
        detailImgeRepository.save(detailImage);

        return new ResponseEntity<>(detailImage, HttpStatus.CREATED);
    }
    //fix error 406: [org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }

    @GetMapping("/detailimglist/{detail_id}")
    public List<DetailImage> getDetailImgByDetailId(@PathVariable(value = "detail_id") long detail_id){
        return detailImgeRepository.listDetailImageByDetailId(detail_id);
    }

    //serve image
    @RequestMapping(value = "detailimage/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public  byte[] getImageDetails(@PathVariable(value = "imageName") String imageName) throws IOException {

        File serverFile = new File("uploads/details/" + imageName);

        return Files.readAllBytes(serverFile.toPath());
    }
}
