package com.microservice.marketplace.repositories;

import com.microservice.marketplace.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositories extends JpaRepository<Order, String> {
}
