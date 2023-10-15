//package com.example.shop.controller;
//
//import com.example.shop.entity.Product;
//import com.example.shop.repository.CategoryRepository;
//import com.example.shop.repository.ProductRepository;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.xml.crypto.Data;
//import java.util.List;
//
//@Controller
//@RequestMapping(path = "/data_controller")
//public class DataController {
//
//    private final ProductRepository productRepository;
//
//
//    public DataController(ProductRepository productRepository){
//        this.productRepository = productRepository;
//    }
//
//    @GetMapping(path = "/second_resource")
//    public String secondResource(Model model){
//        List<Product> products = productRepository.findAll();
//        model.addAttribute("products",products);
//        return "data/data_resource_page";
//    }
//
//    @GetMapping(path = "/third_resource")
//    public String thirdResource(
//            @RequestParam(name = "category",required = false) String name,
//            Model model
//    ){
//        List<Product> products = productRepository.findAllByCategoryName(name);
//        model.addAttribute("products",products);
//        if(name==null){
//            products = productRepository.findAll();
//            model.addAttribute("products",products);
//        }
//        return "data/data_resource_page";
//    }
//
//    @GetMapping(path = "/four_resource")
//    public String fourResource(
//            Model model,
//            @RequestParam(name = "page",required = false) Integer page
//            ){
//        if(page!=null){
//            if(page>=0) {
//                Pageable pageable = PageRequest.of(page, 2);
//                Page<Product> productsPage = productRepository.findAll(pageable);
//                List<Product> products = productsPage.getContent();
//                model.addAttribute("products", products);
//            }
//        } else{
//            List<Product> products = productRepository.findAll();
//            model.addAttribute("products", products);
//        }
//         return "data/data_resource_page";
//    }
//}
