package com.microservice.warehouse.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_warehouse")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "product_stock")
    private double productStock;

    @Column(name = "product_category")
    private String productCategory;
    @Column(name = "product_created_date")
    private String productCreatedDate;
    @Column(name = "product_updated_date")
    private String productUpdatedDate;
}

