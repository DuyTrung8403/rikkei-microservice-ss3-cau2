package com.rikkei.microservice.customerservice.controller;

import com.rikkei.microservice.customerservice.dto.CustomerRequestDTO;
import com.rikkei.microservice.customerservice.dto.CustomerResponseDTO;
import com.rikkei.microservice.customerservice.dto.LoginRequestDTO;
import com.rikkei.microservice.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDTO> register(@Valid @RequestBody CustomerRequestDTO requestDTO) {
        CustomerResponseDTO response = customerService.register(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getById(@PathVariable("id") Long id) {
        CustomerResponseDTO response = customerService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/login")
    public ResponseEntity<CustomerResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginDTO) {
        CustomerResponseDTO response = customerService.login(loginDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }
}
