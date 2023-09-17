package com.example.back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllOrdersDto {
    private int page = 0;
    private int size = 5;
    private String[] sorts = {"orderDate,desc"};
    private Integer userId;
}
