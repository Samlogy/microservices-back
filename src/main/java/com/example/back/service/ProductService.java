package com.example.back.service;

import com.example.back.dto.CreateProductRequest;
import com.example.back.exception.NotFoundException;
import com.example.back.model.Product;
import com.example.back.repository.ProductRepository;
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
public class ProductService {
    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Map<String, Object> getProducts(String name, String category, float priceMin, float priceMax, String sortOrder, int page, int size) {
        Pageable paging = PageRequest.of(page, size);

        Page<Product> pageProds;
//        if (name != null)

//        if (name == null)
//            pageProds = productRepository.findAll(paging);
//        else
            pageProds = productRepository.filterProducts(name, category, priceMin, priceMax, paging);

        Map<String, Object> response = new HashMap<>();
        response.put("products", pageProds.getContent());
        response.put("currentPage", pageProds.getNumber());
        response.put("totalItems", pageProds.getTotalElements());
        response.put("totalPages", pageProds.getTotalPages());

        return  response;
    }

    public Product getProduct(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product do not exist with this ID: " + id));
    }

    public int createProduct(CreateProductRequest requestDto) {
        Product newProduct = Product.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .quantity(requestDto.getQuantity())
                .price(requestDto.getPrice())
                .category(requestDto.getCategory())
                .image(requestDto.getImage())
                .build();
        return productRepository.save(newProduct).getId();
    }
}