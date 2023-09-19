package com.example.back.service;

import com.example.back.dto.Shipping.CreateShippingRequestDto;
import com.example.back.model.Shipping;
import com.example.back.repository.ShippingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShippingService {
    private final ShippingRepository shippingRepository;


    @Autowired
    public ShippingService(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    public int createShipping(CreateShippingRequestDto requestDto) {
        Shipping newShipping = Shipping.builder()
                .email(requestDto.getEmail())
                .address(requestDto.getAddress())
                .contact(requestDto.getContact())
                .build();
        return shippingRepository.save(newShipping).getId();
    }
}
