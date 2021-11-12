package com.example.shoplist.controller;

import com.example.shoplist.dto.ShopDto;
import com.example.shoplist.service.ShopService;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/shoplist")
    public String viewlist(Model model) {
        List<ShopDto> shop = shopService.getAllShop();
        model.addAttribute("shops", shop);
        return "index";
    }

//    @RequestMapping("/")
//    public String listshop(Model model,
//                           @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
//        Sort sortable = null;
//        if (sort.equals("ASC")){
//            sortable = Sort.by("id").ascending();
//            Pageable pageable = PageRequest.of(page, size, sortable);
//        }
//    }
}
