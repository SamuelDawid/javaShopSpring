package com.example.javashopspring.mapper;

import org.javashop.domain.resources.Electronics;
import org.javashop.dto.productDTO.CreateProductCommand;
import org.javashop.dto.productDTO.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toResponse(Electronics electronics);
    Electronics toEntity(CreateProductCommand productCommand);
}
