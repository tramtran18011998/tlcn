package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;
import com.example.furniturewebdemo1.model.Cart;
import com.example.furniturewebdemo1.model.Customer;
import com.example.furniturewebdemo1.model.Product;
import com.example.furniturewebdemo1.repository.CartRepository;
import com.example.furniturewebdemo1.service.CartService;
import com.example.furniturewebdemo1.service.CustomerService;
import com.example.furniturewebdemo1.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartRepository cartRepository;

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @GetMapping("/cart")
    public List<Cart> getAllCart(){
        return cartService.findAllCart();
    }



    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Cart cart=cartService.findCartById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        return ResponseEntity.ok().body(cart);
    }

    @GetMapping("/cartcheck/{product_id}/{customer_id}")
    public Cart checkCartExistProCus(@PathVariable(value = "product_id") long product_id,@PathVariable(value = "customer_id") long customer_id) throws ResourceNotFoundException {
        //Cart cart=cartService.findCartById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        Cart cart1 = cartRepository.checkCartExistProCus(product_id,customer_id);
        return cart1;
    }

    @GetMapping("/check")
    public Cart checkC() throws ResourceNotFoundException {
        //Cart cart=cartService.findCartById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        Cart cart1 = checkCartExistProCus(16,5);
        return cart1;
    }

    @GetMapping("/cartcount/{id}")
    public long countQuantity(@PathVariable(value = "id") long id){
        return cartRepository.countQuantity(id);
    }

    @GetMapping("/cartlist/{id}")
    public List<Cart> getListCartByCustomer(@PathVariable(value = "id") long id){
        return cartRepository.getListCartByCustomer(id);
    }

//    @PostMapping("/cart")
//    public  Cart createCartCheck(@Valid @RequestBody Cart cart) throws ResourceNotFoundException {
//        //Product product = cart.getProduct();
//        Product product = cart.getProduct();
//        logger.info(String.valueOf(product.getId()));
//        Customer customer = cart.getCustomer();
//        logger.info(String.valueOf(customer.getId()));
//
//        Cart cart1 = checkCartExistProCus(product.getId(), customer.getId());
//        logger.info(String.valueOf(cart1));
//
//        //exist
//        if(cart1 != null){
//            cart1.setQuantity(cart1.getQuantity()+1);
//            logger.info(String.valueOf(cart1.getQuantity()));
//            cart1.setTotalprice(cart1.getPrice()*cart1.getQuantity());
//            logger.info(String.valueOf(cart1.getTotalprice()));
//            updateCart(cart1.getId(), cart1);
//            logger.info("null");
//            long count =cartRepository.countQuantity(customer.getId());
//            logger.info(String.valueOf(count));
//            return cart1;
//        }
//        else {
//            //create new cart
//            logger.info("save");
//            cartService.save(cart);
//            return cart;
//        }
//
//    }

    @PostMapping("/cart/{idpro}/{idcus}")
    public  Cart createCartCheck(@PathVariable(value = "idpro") long idpro,@PathVariable(value = "idcus") long idcus,@Valid @RequestBody Cart cart) throws ResourceNotFoundException {
        //Product product = cart.getProduct();
        Product product = productService.findProductById(idpro).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        logger.info(String.valueOf(product.getId()));
        Customer customer = customerService.findCustomerId(idcus).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        logger.info(String.valueOf(customer.getId()));

        Cart cart1 = checkCartExistProCus(product.getId(), customer.getId());
        logger.info(String.valueOf(cart1));

        cart.setCustomer(customer);
        cart.setProduct(product);
        //exist
        if(cart1 != null){
            cart1.setQuantity(cart1.getQuantity()+cart.getQuantity());
            logger.info(String.valueOf(cart1.getQuantity()));
            cart1.setTotalprice(cart1.getPrice()*cart1.getQuantity());
            logger.info(String.valueOf(cart1.getTotalprice()));
            updateCart(cart1.getId(), cart1);
            logger.info("null");
            long count =cartRepository.countQuantity(customer.getId());
            logger.info(String.valueOf(count));
            return cart1;
        }
        else {
            //create new cart
            logger.info("save");
            cartService.save(cart);
            return cart;
        }

    }

//    @PostMapping("/cart")
//    public  ResponseEntity<Cart> createCart(@Valid @RequestBody Cart cart){
//        cartService.save(cart);
//        return new ResponseEntity<>(cart, HttpStatus.CREATED);
//    }

    @PutMapping("/cart/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable(value = "id") long id, @Valid @RequestBody Cart cart) throws ResourceNotFoundException {
        Cart currentCart= cartService.findCartById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found"));

        if(cart.getProduct()!= null){
            currentCart.setProduct(cart.getProduct());
        }
        if(cart.getCustomer()!= null){
            currentCart.setCustomer(cart.getCustomer());
        }
        if (cart.getPrice()!=0){
            currentCart.setPrice(cart.getPrice());
        }
        if(cart.getProductname()!=null){
            currentCart.setProductname(cart.getProductname());
        }
        if(cart.getQuantity()!=0){
            currentCart.setQuantity(cart.getQuantity());
        }
        if(cart.getTotalprice()!=0){
            currentCart.setTotalprice(cart.getTotalprice());
        }
        if(cart.getStatus()!=0){
            currentCart.setStatus(cart.getStatus());
        }

        cartService.save(currentCart);
        return ResponseEntity.ok(currentCart);

    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Cart cart=cartService.findCartById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        cartService.delete(cart);
        return ResponseEntity.ok(cart);
    }
}
