package com.example.back.resource;

import com.example.back.dto.CreateProductRequest;
import com.example.back.dto.CreateShippingRequest;
import com.example.back.repository.ProductRepository;
import com.example.back.service.ProductService;
import com.example.back.service.ShippingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/shipping")
@Slf4j
@Validated
public class ShippingResource {
    private final ShippingService shippingService;

    public ShippingResource(ShippingService shippingService ) {
        this.shippingService = shippingService;
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody CreateShippingRequest requestDto){
        int id = shippingService.createShipping(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
