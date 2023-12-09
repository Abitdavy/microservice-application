package com.microservice.warehouse.service;

import com.microservice.warehouse.Entity.Product;
import com.microservice.warehouse.model.ProductDTO;
import com.microservice.warehouse.repositories.ProductRepositories;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepositories productRepositories;

    public ProductService(ProductRepositories productRepositories) {
        this.productRepositories = productRepositories;
    }

    public List<Product> getAllProduct(){
        List<Product> productList = new ArrayList<>();

        try {
            productList = productRepositories.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return productList;
    }

    public Product createProduct(ProductDTO prod){
        Product productEntity = new Product();

        if (prod.getProductName().isEmpty() || prod.getProductPrice() == 0 || prod.getProductCategory().isEmpty() || prod.getProductStock() == 0){
            throw new IllegalArgumentException("data kosong");
        }else {
            productEntity.setProductName(prod.getProductName());
            productEntity.setProductPrice(prod.getProductPrice());
            productEntity.setProductCategory(prod.getProductCategory());
            productEntity.setProductStock(prod.getProductStock());
            productEntity.setProductCreatedDate(LocalDateTime.now().toString());

            try {
                productRepositories.save(productEntity);
            }catch (Exception e){
                throw new IllegalArgumentException(e.getMessage());
            }

            return productEntity;
        }
    }

    public Product getProductById(Long id){;
        Product productEntity = productRepositories.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        return productEntity;
    }

    public Product updateProduct(ProductDTO prod){
        Product productEntity;

        if (prod.getProductName().isEmpty() || prod.getProductPrice() == 0) {
            throw new IllegalArgumentException("data null");
        } else {
            productEntity = productRepositories.findById(prod.getId()).orElseThrow(() -> new IllegalArgumentException("Product not found"));

            productEntity.setProductName(prod.getProductName());
            productEntity.setProductPrice(prod.getProductPrice());
            productEntity.setProductCategory(prod.getProductCategory());
            productEntity.setProductStock(prod.getProductStock());
            productEntity.setProductUpdatedDate(LocalDateTime.now().toString());

            try {
                productRepositories.save(productEntity);
            }catch (Exception e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        return productEntity;
    }

    public Product deleteProduct(Long id){

        Product product = productRepositories.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        try {
            productRepositories.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }

        return product;
    }

    public List<Product> orderedProduct(List<ProductDTO> prod){
        List<Product> products = new ArrayList<>();

        for (ProductDTO product: prod) {
            Optional<Product> productEntity = productRepositories.findById(product.getId());

            if (productEntity == null) {
                productEntity.get().setProductName(product.getProductName());
                productEntity.get().setProductPrice(product.getProductPrice());
                productEntity.get().setProductCategory(product.getProductCategory());
                productEntity.get().setProductStock(product.getProductStock());
                productEntity.get().setProductCreatedDate(LocalDateTime.now().toString());
                products.add(productEntity.get());
            } else {
                productEntity.get().setProductName(product.getProductName());
                productEntity.get().setProductPrice(product.getProductPrice());
                productEntity.get().setProductStock(productEntity.get().getProductStock() - product.getProductStock());
                productEntity.get().setProductUpdatedDate(LocalDateTime.now().toString());
                products.add(productEntity.get());
            }

            try {
                productRepositories.save(productEntity.get());
            }catch (Exception e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }


        return products;
    }
}
