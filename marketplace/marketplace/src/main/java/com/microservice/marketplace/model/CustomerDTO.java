package com.microservice.marketplace.model;

import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CustomerDTO {
    private Long customerId;
    private String customerName;
    private String customerAge;
    private String customerEmail;
}
