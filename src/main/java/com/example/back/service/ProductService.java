package com.example.back.service;

import com.example.back.dto.CreateProductRequest;
import com.example.back.dto.GetProductsRequest;
import com.example.back.exception.NotFoundException;
import com.example.back.model.Product;
import com.example.back.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    public Map<String, Object> getProducts(GetProductsRequest requestDto) {
        List<Sort.Order> sortOrders = new ArrayList<Sort.Order>();
        Page<Product> pageProds;

        String[] sorts = requestDto.getSorts();

        if (sorts[0].contains(",")) {
            for (String sortOrder : sorts) {
                String[] _sort = sortOrder.split(",");
                sortOrders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            sortOrders.add(new Sort.Order(getSortDirection(sorts[1]), sorts[0]));
        }
        Pageable pageRequest = PageRequest.of(requestDto.getPage(), requestDto.getSize(), Sort.by(sortOrders));

        if (requestDto.getName() == null)
            pageProds = productRepository.findAll(pageRequest);
        else
            pageProds = productRepository.findByNameContainingAndCategoryContainingAndPriceBetween(requestDto.getName(),
                    requestDto.getCategory(),
                    requestDto.getPriceMin(),
                    requestDto.getPriceMax(),
                    pageRequest);

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

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) return Sort.Direction.DESC;
        return Sort.Direction.ASC;
    }
}