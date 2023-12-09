package com.microservice.warehouse.repositories;

import com.microservice.warehouse.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositories extends JpaRepository<Product, Long> {
}
