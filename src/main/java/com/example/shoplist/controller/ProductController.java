package com.example.shoplist.controller;

import com.example.shoplist.dto.ProductDto;
import com.example.shoplist.dto.ShopDto;
import com.example.shoplist.model.Product;
import com.example.shoplist.service.ProductService;
import com.example.shoplist.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired private ShopService shopService;
//    @GetMapping("/shopform")
//    public String viewProductList(@RequestParam("id") Integer id, Model model, RedirectAttributes ra){
//        List<ProductDto> p = productService.getAllProduct();
//        model.addAttribute("product", p);
//        return "shopform";
//    }

    @GetMapping("shopform")
    public String index(@RequestParam("id") Integer id, Model model) {
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

    @GetMapping("/sortproduct")
    public String sort(@RequestParam("id") Integer id, Model model) {
        List<ProductDto> check = productService.sortProduct(id);
        if (check != null) {
            model.addAttribute("product", check);
            model.addAttribute("idshop", id);
            return "shopform";
        } else {
//            ra.addAttribute("id", id);
            return "NotFound";
        }
    }
//    @GetMapping("/buy")
//    public String Buy(@RequestParam("soluong") List<Integer> soluong,@RequestParam Integer idshop, RedirectAttributes ra){
//        List<ProductDto> check = productService.findProductByIdshop(idshop);
//        boolean over = false;
//        for (int i = 0; i< soluong.size(); i++){
//            if(soluong.get(i) < check.get(i).getQuantity()){
//                check.get(i).setQuantity(check.get(i).getQuantity() - soluong.get(i));
//                check.get(i).setSelledquantity(check.get(i).getSelledquantity()+soluong.get(i));
//            }
//            else{
//                ra.addFlashAttribute("quantityError", "Khong du so luong");
//                over = true;
//            }
//        }
//        if (over){
//            ra.addAttribute("id", idshop);
//            return "redirect:/shopform";
//        }
//        else{
//            productService.saveAllProduct(check);
//            ra.addAttribute("id", idshop);
//            return "redirect:/shopform";
//        }
//    }

    @GetMapping("/buy")
    public String Buy(@RequestParam Integer[] productid, @RequestParam Integer[] soluong, @RequestParam Integer idshop, RedirectAttributes ra) {
        boolean over = false;
        Optional<ShopDto> shopDto = shopService.findShopById(idshop);
        for (int j = 0; j < productid.length; j++) {
            Optional<ProductDto> check = productService.findProductById(productid[j]);
            if (check.get().getQuantity() < soluong[j]) {
                ra.addFlashAttribute("quantityError", "Khong du so luong");
                over = true;
                break;
            }
        }
        if(!over) {
            int total = 0;
            for (int i = 0; i < productid.length; i++) {
                Optional<ProductDto> check = productService.findProductById(productid[i]);
                check.get().setQuantity(check.get().getQuantity() - soluong[i]);
                check.get().setSelledquantity(check.get().getSelledquantity() + soluong[i]);
                productService.saveProduct(check.get());
                total += soluong[i];
            }
            shopDto.get().setTotalselledproduct(shopDto.get().getTotalselledproduct() + total);
            shopService.saveShop(shopDto.get());
            ra.addAttribute("id", idshop);
            return "redirect:/shopform";
        }
        ra.addAttribute("id", idshop);
        return "redirect:/shopform";
    }
}
