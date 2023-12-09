package com.microservice.warehouse.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String productName;
    private double productPrice;
    private double productStock;
    private String productCategory;
}
