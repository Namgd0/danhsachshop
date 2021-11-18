package com.example.shoplist.mapper;

import com.example.shoplist.dto.ProductDto;
import com.example.shoplist.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto modelToDto(Product product);

    Product dtoToModel(ProductDto productDto);

    List<ProductDto> modelstoDtos(List<Product> products);

    List<Product> dtostoModels(List<ProductDto> productDtos);
}
