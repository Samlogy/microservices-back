package com.example.back.service;

import com.example.back.dto.CreateProductRequest;
import com.example.back.dto.CreateShippingRequest;
import com.example.back.exception.NotFoundException;
import com.example.back.model.Product;
import com.example.back.model.Shipping;
import com.example.back.repository.ProductRepository;
import com.example.back.repository.ShippingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShippingService {
    private final ShippingRepository shippingRepository;


    @Autowired
    public ShippingService(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    public int createShipping(CreateShippingRequest requestDto) {
        Shipping newShipping = Shipping.builder()
                .email(requestDto.getEmail())
                .address(requestDto.getAddress())
                .contact(requestDto.getContact())
                .build();
        return shippingRepository.save(newShipping).getId();
    }
}
