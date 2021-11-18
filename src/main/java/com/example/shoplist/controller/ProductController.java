package com.example.shoplist.controller;

import com.example.shoplist.dto.ProductDto;
import com.example.shoplist.dto.ShopDto;
import com.example.shoplist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

//    @GetMapping("/shopform")
//    public String viewProductList(@RequestParam("id") Integer id, Model model, RedirectAttributes ra){
//        List<ProductDto> p = productService.getAllProduct();
//        model.addAttribute("product", p);
//        return "shopform";
//    }

    @GetMapping("shopform")
    public String index(@RequestParam("id") Integer id, Model model, RedirectAttributes ra) {
        List<ProductDto> check = productService.findProductByIdshop(id);
        if (check != null) {
            model.addAttribute("product", check);
            return "shopform";
        } else {
//            ra.addAttribute("id", id);
            return "NotFound";
        }
    }

//    @RequestMapping(value = "/Buy", method = RequestMethod.POST)
//    public String Buy(Model model)
}
