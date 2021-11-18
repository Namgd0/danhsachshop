package com.example.shoplist.service;

import com.example.shoplist.dto.ShopDto;
import com.example.shoplist.mapper.ShopMapper;
import com.example.shoplist.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopMapper shopMapper;


    public List<ShopDto> getAllShop() {
        return shopMapper.modelstoDtos(shopRepository.findAll());
    }

    public void saveShop(ShopDto shopDto) {
        shopRepository.save(shopMapper.dtoToModel(shopDto));
    }


    public Optional<ShopDto> getShopById(Integer id) {
        return Optional.of(shopMapper.modelToDto(shopRepository.findById(id).get()));
    }

    // Truy cập vào trang sản phẩm của từng shop thông qua nút Detail
    public Optional<ShopDto> findShopById(Integer id) {
        return Optional.of(shopMapper.modelToDto(shopRepository.findById(id).get()));
    }


    // Sắp xếp tên shop theo thứ tự ABC
    public List<ShopDto> getShopByNameASC() {
        return shopMapper.modelstoDtos(shopRepository.findAll(Sort.by(Sort.Direction.ASC, "shopname")));
    }

    // Tìm kiếm shop theo tên
    public Optional<ShopDto> findShopByName(String shopname) {
            return Optional.of(shopMapper.modelToDto(shopRepository.findShopByShopname(shopname).get()));
    }

    public List<ShopDto> findAllBySelled() {
        return shopMapper.modelstoDtos(shopRepository.findAllBySelled());
    }

//    public List<ShopDto> findShopBySelledquantity() {
//        return shopMapper.modelstoDtos(shopRepository.findShopBySelledquantity());
//    }
}

