package com.rikkei.microservice.customerservice.dto;

import com.rikkei.microservice.customerservice.entity.Customer;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDTO {

    private Long id;
    private String fullName;
    private String email;
    private String address;

    public static CustomerResponseDTO fromEntity(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
}
