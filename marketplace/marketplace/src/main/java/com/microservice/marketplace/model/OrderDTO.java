package com.microservice.marketplace.model;

import com.microservice.marketplace.entity.Customer;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String orderId;
    private Customer customer;
    private List<ProductDTO> products;
}
