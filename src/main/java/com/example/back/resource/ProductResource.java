package com.example.back.resource;

import com.example.back.dto.Product.CreateProductRequestDto;
import com.example.back.dto.Product.GetProductsRequestDto;
import com.example.back.model.Product;
import com.example.back.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Tag(name = "Product", description = "Product ecommerce api v1.0.0")
@RestController
@RequestMapping("api/v1/product")
@Slf4j
@Validated
public class ProductResource {
    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<?> getProducts(GetProductsRequestDto requestDto){
        Map<String, Object> response = productService.getProducts(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
        Product product = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequestDto requestDto){
        int id = productService.createProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}