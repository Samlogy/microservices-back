package com.example.back.mapper;


import com.example.back.dto.Product.GetProductsRequestDto;
import com.example.back.model.Product;
import org.mapstruct.Mapper;
        import org.mapstruct.Mapping;
        import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "price", target = "price")
    GetProductsRequestDto requestToGetProductsRequestDto(Product product);

    @Mapping(source = "price", target = "price")
    Product GetProductsRequestDtoToRequest(GetProductsRequestDto productDTO);
}