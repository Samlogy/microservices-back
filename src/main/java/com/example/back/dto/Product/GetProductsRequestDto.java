package com.example.back.dto.Product;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProductsRequestDto {
    @Nullable
    private String name;
    @Nullable
    private String category;
    @Nullable
    private Float priceMin;
    @Nullable
    private Float priceMax;
    private String sort = "price,desc";
    private int page = 0;
    private int size = 5;
}
