package com.example.shoplist.repository;

import com.example.shoplist.dto.ProductDto;
import com.example.shoplist.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findProductByProductname(String productname);
    Optional<Product> findProductById(Integer id);
    List<Product> findProductByIdshop(Integer id);
}
