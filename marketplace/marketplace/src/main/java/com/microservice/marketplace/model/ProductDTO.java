package com.microservice.marketplace.model;

import com.microservice.marketplace.entity.Order;
import jakarta.persistence.Column;
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
