package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;
import com.example.furniturewebdemo1.model.Product;
import com.example.furniturewebdemo1.model.ProductImage;
import com.example.furniturewebdemo1.model.User;
import com.example.furniturewebdemo1.repository.ProductImageRepositpry;
import com.example.furniturewebdemo1.repository.ProductRepository;
import com.example.furniturewebdemo1.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepositpry productImageRepositpry;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @GetMapping("/product")
    public List<Product> getAllProduct(){
        return productService.findAllProduct();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Product product=productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/product")
    public  ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){

        //double a= product.getPrice();
        if(product.getDiscountPrice()==0){
            product.setDiscountPrice(product.getPrice());
        }
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") long id, @Valid @RequestBody Product product) throws ResourceNotFoundException {
        Product currentProduct= productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));

        if(product.getColor()!=null){
            currentProduct.setColor(product.getColor());
        }
        if(product.getDescription()!=null){
            currentProduct.setDescription(product.getDescription());
        }
        if(product.getDiscountPrice()==0){
            currentProduct.setDiscountPrice(product.getPrice());
        }
        else {
            currentProduct.setDiscountPrice(product.getDiscountPrice());
        }

        if(product.getMaterial()!=null){
            currentProduct.setMaterial(product.getMaterial());
        }
        if(product.getName()!=null){
            currentProduct.setName(product.getName());
        }
        if (product.getPrice()!=0){
            currentProduct.setPrice(product.getPrice());
        }

        if(product.getQuantity()!=0){
            currentProduct.setQuantity(product.getQuantity());
        }

        if(product.getSize()!=null){
            currentProduct.setSize(product.getSize());
        }
        if (product.getCategory()!=null){
            currentProduct.setCategory(product.getCategory());
        }
        if(product.getSupplier()!=null){
            currentProduct.setSupplier(product.getSupplier());
        }


        productService.save(currentProduct);
        return ResponseEntity.ok(currentProduct);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Product product=productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        productService.delete(product);
        return ResponseEntity.ok(product);
    }

    public String storeImg(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File convertFile = new File("uploads/products/"+fileName);
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
    @PostMapping("/proimage")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException, ResourceNotFoundException {

        return storeImg(file);
    }


    @PostMapping("/proimages")
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

//    @PostMapping("/productimage")
//    public  ResponseEntity<ProductImage> createProductImage(@Valid @ModelAttribute ProductImage productImage){
//        productImageRepositpry.save(productImage);
//        return new ResponseEntity<>(productImage, HttpStatus.CREATED);
//    }
    @PostMapping(value = "/productimg/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> createProduct(@RequestParam("files") MultipartFile[] files,@PathVariable(value = "id") long id) throws IOException, ResourceNotFoundException {

        //ProductImage productImage = new ProductImage();
        Product product = productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        logger.info(product.getName());
        //productImage.setProduct(product);
        List<String> imglist = uploadFiles(files);
        imglist.forEach(a -> {
            ProductImage productImage1 = new ProductImage();
            productImage1.setName(a);
            productImage1.setProduct(product);
            productImageRepositpry.save(productImage1);
        });

        return (ResponseEntity<?>) ResponseEntity.ok();
    }
    //fix error 406: [org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }

    @GetMapping("/productimg/{product_id}")
    public List<ProductImage> getProductByProductId(@PathVariable(value = "product_id") long product_id) throws ResourceNotFoundException {
        return productRepository.listProductImageByProductId(product_id);
    }
}
