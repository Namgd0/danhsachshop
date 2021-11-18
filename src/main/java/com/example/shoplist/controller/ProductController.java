package com.example.shoplist.controller;

import com.example.shoplist.dto.ProductDto;
import com.example.shoplist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
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
            model.addAttribute("idshop", id);
            return "shopform";
        } else {
//            ra.addAttribute("id", id);
            return "NotFound";
        }
    }

    @GetMapping("/buy")
    public String Buy(@RequestParam("soluong") List<Integer> soluong,@RequestParam Integer idshop, RedirectAttributes ra){
        List<ProductDto> check = productService.findProductByIdshop(idshop);
        boolean over = false;
        for (int i = 0; i< soluong.size(); i++){
            if(soluong.get(i) < check.get(i).getQuantity()){
                check.get(i).setQuantity(check.get(i).getQuantity() - soluong.get(i));
                check.get(i).setSelledquantity(check.get(i).getSelledquantity()+soluong.get(i));
            }
            else{
                ra.addFlashAttribute("quantityError", "Khong du so luong");
                over = true;
            }
        }
        if (over){
            ra.addAttribute("id", idshop);
            return "redirect:/shopform";
        }
        else{
            productService.saveAllProduct(check);
            ra.addAttribute("id", idshop);
            return "redirect:/shopform";
        }
    }
}
