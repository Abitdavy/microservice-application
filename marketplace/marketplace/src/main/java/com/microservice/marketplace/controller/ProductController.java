//package com.microservice.marketplace.controller;
//
//import com.microservice.marketplace.model.CustomerDTO;
//import com.microservice.marketplace.model.ProductDTO;
//import com.microservice.marketplace.model.ResponseDTO;
//import com.microservice.marketplace.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/product")
//public class ProductController {
//    private final ProductService productService;
//
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    @GetMapping(path = "/get-all")
//    public ResponseEntity<ResponseDTO> getProductAll(){
//        ResponseDTO responseDTO = productService.getAllProduct();
//        return ResponseEntity.ok().body(responseDTO);
//    }
//
//    @PostMapping(path = "/create")
//    public ResponseEntity<ResponseDTO> createProduct(@RequestBody ProductDTO productDTO){
//        ResponseDTO responseDTO = productService.createProduct(productDTO);
//        return ResponseEntity.ok().body(responseDTO);
//    }
//
//    @GetMapping(path = "/id={id}")
//    public ResponseEntity<ResponseDTO> getCustomerById(@PathVariable Long id){
//        ResponseDTO responseDTO = productService.getProductById(id);
//        return ResponseEntity.ok().body(responseDTO);
//    }
//
//    @PutMapping(path = "/update")
//    public ResponseEntity<ResponseDTO> getCustomerById(@RequestBody ProductDTO productDTO){
//        ResponseDTO responseDTO = productService.updateProduct(productDTO);
//        return ResponseEntity.ok().body(responseDTO);
//    }
//
//    @DeleteMapping(path = "/delete-id={id}")
//    public ResponseEntity<ResponseDTO> deleteCustomerById(@PathVariable Long id){
//        ResponseDTO responseDTO = productService.deleteProduct(id);
//        return ResponseEntity.ok().body(responseDTO);
//    }
//}
