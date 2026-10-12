package com.rikkei.microservice.customerservice.service;

import com.rikkei.microservice.customerservice.dto.CustomerRequestDTO;
import com.rikkei.microservice.customerservice.dto.CustomerResponseDTO;
import com.rikkei.microservice.customerservice.dto.LoginRequestDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO register(CustomerRequestDTO requestDTO);
    CustomerResponseDTO getById(Long id);
    CustomerResponseDTO login(LoginRequestDTO loginDTO);
    List<CustomerResponseDTO> getAll();
}
