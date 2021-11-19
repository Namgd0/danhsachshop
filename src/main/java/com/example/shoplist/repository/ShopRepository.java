package com.example.shoplist.repository;

import com.example.shoplist.dto.ShopDto;
import com.example.shoplist.model.Product;
import com.example.shoplist.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    Optional<Shop> findShopByShopname(String shopname);

    @Query(value = "select * from shop where totalselledproduct >= 100 order by totalselledproduct DESC limit 10", nativeQuery = true)
    List<Shop> findAllBySelled();
}
