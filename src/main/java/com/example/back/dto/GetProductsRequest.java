package com.example.back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProductsRequest {
    private String name;
    private String category;
    private Float priceMin;
    private Float priceMax;
    private String[] sorts = {"price,desc"};
    private int page = 0;
    private int size = 5;
}
