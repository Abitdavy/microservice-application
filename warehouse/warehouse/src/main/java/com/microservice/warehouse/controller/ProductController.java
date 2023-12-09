package com.microservice.warehouse.controller;

import com.microservice.warehouse.Entity.Product;
import com.microservice.warehouse.model.ProductDTO;
import com.microservice.warehouse.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<Product>> getProductAll(){
        List<Product> product = productService.getAllProduct();
        return ResponseEntity.ok().body(product);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO){
        Product product = productService.createProduct(productDTO);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping(path = "/id={id}")
    public ResponseEntity<Product> getCustomerById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Product> getCustomerById(@RequestBody ProductDTO productDTO){
        Product product = productService.updateProduct(productDTO);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping(path = "/delete-id={id}")
    public ResponseEntity<Product> deleteCustomerById(@PathVariable Long id){
        Product product = productService.deleteProduct(id);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping(path = "/order-product")
    public ResponseEntity<List<Product>> orderedProduct(@RequestBody List<ProductDTO> productDTO){
        List<Product> product = productService.orderedProduct(productDTO);
        return ResponseEntity.ok().body(product);
    }
}
