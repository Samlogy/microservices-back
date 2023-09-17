package com.example.back.resource;

import com.example.back.dto.CreateProductRequest;
import com.example.back.model.Product;
import com.example.back.repository.ProductRepository;
import com.example.back.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.back.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/product")
@Slf4j
@Validated
public class ProductResource {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductResource(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getProducts(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) String category,
                                                     @RequestParam(required = false) Float priceMin,
                                                     @RequestParam(required = false) Float priceMax,
                                                     @RequestParam(required = false) String sortOrder,
                                                     @RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "5") int size){
        Map<String, Object> response = productService.getProducts(name, category, priceMin, priceMax, sortOrder, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
        Product product = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest requestDto){
        int id = productService.createProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}