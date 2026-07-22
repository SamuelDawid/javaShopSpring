package com.example.javashopspring.mapper;

import com.example.javashopspring.domain.resources.Electronics;
import com.example.javashopspring.dto.productDTO.CreateProductCommand;
import com.example.javashopspring.dto.productDTO.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toResponse(Electronics electronics);
    Electronics toEntity(CreateProductCommand productCommand);
}
