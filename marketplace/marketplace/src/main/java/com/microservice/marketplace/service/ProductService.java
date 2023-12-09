//package com.microservice.marketplace.service;
//
//import com.microservice.marketplace.model.ProductDTO;
//import com.microservice.marketplace.model.ResponseDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Service
//public class ProductService {
//    private static final String PRODUCT_SERVICE_URL = "http://localhost:9090/product/";
//
//    private final RestTemplate restTemplate;
//
//    public ProductService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public ResponseDTO getAllProduct(){
//        List<ProductDTO> productList = new ArrayList<>();
//        ResponseDTO responseDTO = new ResponseDTO<>();
//
//        HttpHeaders headers = new HttpHeaders();
//        ProductDTO productDTO = restTemplate.getForObject(PRODUCT_SERVICE_URL, ProductDTO.class);;
//
//        if (productDTO == null){
//            responseDTO.setStatus("failed");
//            responseDTO.setMessage("data not found");
//            responseDTO.setData(productList);
//        } else {
//            productList.add(productDTO)
//            responseDTO.setStatus("success");
//            responseDTO.setMessage("data found");
//            responseDTO.setData(productList);
//        }
//
//        return responseDTO;
//    }
//
//    public ResponseDTO createProduct(ProductDTO prod){
//
//        List<ProductDTO> productList = new ArrayList<>();
//        ResponseDTO responseDTO = new ResponseDTO<>();
//
//        HttpHeaders headers = new HttpHeaders();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<ProductDTO> entity = new HttpEntity<>(prod, headers);
//        ResponseEntity<ProductDTO> response = restTemplate.exchange(
//                (PRODUCT_SERVICE_URL + "create"),
//                HttpMethod.POST, entity,
//                ProductDTO.class
//        );
//        ProductDTO productDTO = response.getBody();
//
//        if (productDTO.getProductName().isEmpty() || productDTO.getProductPrice() == 0) {
//            responseDTO.setStatus("failed");
//            responseDTO.setMessage("data yang diinput kosong");
//            responseDTO.setData(productList);
//        } else {
//            productList.add(productDTO);
//            responseDTO.setStatus("success");
//            responseDTO.setMessage("data created");
//            responseDTO.setData(productList);
//        }
//
//        return responseDTO;
//    }
//
//    public ProductDTO getProductById(Long id){
//        List<ProductDTO> productList = new ArrayList<>();
//        ResponseDTO responseDTO = new ResponseDTO<>();
//
//        HttpHeaders headers = new HttpHeaders();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<ProductDTO> entity = new HttpEntity<>(null, headers);
//        ResponseEntity<ProductDTO> response = restTemplate.exchange(
//                (PRODUCT_SERVICE_URL + "/" + String.valueOf(id)),
//                HttpMethod.GET, entity,
//                ProductDTO.class
//        );
//        ProductDTO productDTO = response.getBody();
//        productList.add(productDTO);
//
//        responseDTO.setStatus("success");
//        responseDTO.setMessage("data found");
//        responseDTO.setData(productList);
//        return responseDTO;
//    }
//
//    public ProductDTO updateProduct(ProductDTO prod){
//        List<Product> productList = new ArrayList<>();
//        ResponseDTO responseDTO = new ResponseDTO<>();
//
//        if (prod.getProductName().isEmpty() || prod.getProductPrice() == 0) {
//            responseDTO.setStatus("failed");
//            responseDTO.setMessage("data yang diinput kosong");
//            responseDTO.setData(productList);
//        } else {
//            Product productEntity = productRepositories.findById(prod.getId()).orElseThrow(() -> new IllegalArgumentException("Product not found"));
//
//            productEntity.setProductName(prod.getProductName());
//            productEntity.setProductPrice(prod.getProductPrice());
//
//            try {
//                productRepositories.save(productEntity);
//                productList.add(productEntity);
//            }catch (Exception e){
//                throw new IllegalArgumentException(e.getMessage());
//            }
//
//            responseDTO.setStatus("success");
//            responseDTO.setMessage("data saved");
//            responseDTO.setData(productList);
//        }
//
//        return responseDTO;
//    }
//
//    public ResponseDTO deleteProduct(Long id){
//        List<Product> productList = new ArrayList<>();
//        ResponseDTO responseDTO = new ResponseDTO<>();
//
//        Product customerEntity = productRepositories.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
//        productList.add(customerEntity);
//        productRepositories.deleteById(id);
//
//        responseDTO.setStatus("success");
//        responseDTO.setMessage("data deleted");
//        responseDTO.setData(productList);
//        return responseDTO;
//    }
//}
