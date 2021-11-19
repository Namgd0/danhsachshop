package com.example.shoplist.service;

import com.example.shoplist.dto.ProductDto;
import com.example.shoplist.mapper.ProductMapper;
import com.example.shoplist.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductRepository productRepository;

    @Autowired private ProductMapper productMapper;

    public List<ProductDto> getAllProduct() {
        return productMapper.modelstoDtos(productRepository.findAll());
    }

    public void saveProduct(ProductDto productDto) {
        productRepository.save(productMapper.dtoToModel(productDto));
    }

    public void saveAllProduct(List<ProductDto> productDtoList) {
        productRepository.saveAll(productMapper.dtostoModels(productDtoList));
    }

    public List<ProductDto> getProductByName() {
        return productMapper.modelstoDtos(productRepository.findAll(Sort.by(Sort.Direction.ASC,"productname")));
    }
    public Optional<ProductDto> findProductById(Integer id) {
        try{
            return Optional.of(productMapper.modelToDto(productRepository.findProductById(id).get()));
        } catch (Exception ex){
            return Optional.ofNullable(null);
        }
    }
    public List<ProductDto> findProductByIdshop(Integer id){
        return productMapper.modelstoDtos(productRepository.findProductByIdshop(id));
    }
    public List<ProductDto> sortProduct(Integer idshop){
        return productMapper.modelstoDtos(productRepository.findProductByIdshopOrderByProductnameAsc(idshop));
    }
}
