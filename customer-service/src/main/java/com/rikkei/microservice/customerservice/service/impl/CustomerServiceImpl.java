package com.rikkei.microservice.customerservice.service.impl;

import com.rikkei.microservice.customerservice.dto.CustomerRequestDTO;
import com.rikkei.microservice.customerservice.dto.CustomerResponseDTO;
import com.rikkei.microservice.customerservice.dto.LoginRequestDTO;
import com.rikkei.microservice.customerservice.entity.Customer;
import com.rikkei.microservice.customerservice.exception.BadRequestException;
import com.rikkei.microservice.customerservice.exception.ResourceNotFoundException;
import com.rikkei.microservice.customerservice.repository.CustomerRepository;
import com.rikkei.microservice.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerResponseDTO register(CustomerRequestDTO requestDTO) {
        if (customerRepository.existsByEmail(requestDTO.getEmail())) {
            throw new BadRequestException("Email đã được sử dụng: " + requestDTO.getEmail());
        }

        Customer customer = Customer.builder()
                .fullName(requestDTO.getFullName())
                .email(requestDTO.getEmail())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .address(requestDTO.getAddress())
                .build();

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerResponseDTO.fromEntity(savedCustomer);
    }

    @Override
    public CustomerResponseDTO getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        return CustomerResponseDTO.fromEntity(customer);
    }

    @Override
    public CustomerResponseDTO login(LoginRequestDTO loginDTO) {
        Customer customer = customerRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new BadRequestException("email or password incorrect"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), customer.getPassword())) {
            throw new BadRequestException("email or password incorrect");
        }

        return CustomerResponseDTO.fromEntity(customer);
    }

    @Override
    public List<CustomerResponseDTO> getAll() {
        return customerRepository.findAll().stream()
                .map(CustomerResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
