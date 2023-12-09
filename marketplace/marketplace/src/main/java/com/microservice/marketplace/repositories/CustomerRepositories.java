package com.microservice.marketplace.repositories;

import com.microservice.marketplace.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface CustomerRepositories extends JpaRepository<Customer, Long> {
}
