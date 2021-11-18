package com.example.shoplist.controller;

import com.example.shoplist.dto.ProductDto;
import com.example.shoplist.dto.ShopDto;
import com.example.shoplist.model.Shop;
import com.example.shoplist.service.ProductService;
import com.example.shoplist.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ShopController {
    @Autowired
    private ShopService shopService;
    private ProductService productService;

    @GetMapping("/")
    public String viewlist(Model model) {
        List<ShopDto> shop = shopService.getShopByNameASC();
        List<ShopDto> shopl = shopService.findAllBySelled();
        model.addAttribute("shops", shop);
        model.addAttribute("shop", shopl);
        return "index";
    }


    @GetMapping("/search")
    public String search(Model model, @RequestParam("keyword") String keyword) {
        Optional<ShopDto> shop = shopService.findShopByName(keyword);
        shop.ifPresent(o -> model.addAttribute("shop", o));
        return "index";
    }

//    @RequestMapping(value = "Buy")
//    public String BuyProduct(Model model, @RequestParam("selledquantity") Integer selledquantity) {
//        List<ShopDto> sold = shopService.findShopBySelledquantity();
//        if()
//    }

}
