package com.example.shoplist.mapper;

import com.example.shoplist.dto.ShopDto;
import com.example.shoplist.model.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

    ShopDto modelToDto(Shop shop);

    Shop dtoToModel(ShopDto shopDto);

    List<ShopDto> modelstoDtos(List<Shop> shops);

}
