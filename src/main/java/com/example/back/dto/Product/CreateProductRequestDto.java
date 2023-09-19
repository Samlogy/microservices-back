package com.example.back.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequestDto {
    private Integer id;
    private String name;
    private String description;
    private int quantity;
    private float price;
    private String image;
    private String category;
}
