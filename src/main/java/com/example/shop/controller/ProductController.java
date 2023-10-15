package com.example.shop.controller;

import com.example.shop.entity.*;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.ReviewRepository;
import com.example.shop.service.CartService;

import com.example.shop.service.ProductService;
import com.example.shop.service.ReviewService;
import com.example.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
    @RequestMapping(path = "/products")
    public class ProductController {

        private final ProductRepository productRepository;

        private final CategoryRepository categoryRepository;

        private final  ReviewRepository reviewRepository;

        private final ReviewService reviewService;

        private final CartRepository cartRepository;

        private final UserService userService;

        private final CartService cartService;

        private final ProductService productService;



        public ProductController(ProductRepository productRepository,
                                 CategoryRepository categoryRepository,
                                 ReviewRepository reviewRepository,
                                 ReviewService reviewService,
                                 CartRepository cartRepository,
                                 UserService userService,
                                 CartService cartService,
                                 ProductService productService){
            this.productRepository = productRepository;
            this.categoryRepository = categoryRepository;
            this.reviewRepository = reviewRepository;
            this.reviewService = reviewService;
            this.cartRepository = cartRepository;
            this.userService = userService;
            this.cartService = cartService;
            this.productService = productService;
        }

        @GetMapping(path = "/create_product")
        public String createProductController(Model model){
            List<Category> categoryList = productService.getFindAllCategory();
            model.addAttribute(categoryList);
            return "data/form_page";
        }

        @GetMapping
        public String productList(
                Model model
        ){
            List<Product> products;
//            products = productRepository.findAll();
            products = productService.getFindAllProduct();
            model.addAttribute("products",products);
            return "data/data_resource_page";
        }

        @PostMapping
        public String addToCart(
                @RequestParam(name = "addCart") long addCart
        ){
            User currentUser = userService.getCurrentUser();
//            Product product = productRepository.findById(addCart).orElse(null);
            Product product = productService.getFindProductById(addCart);
            if(currentUser== null){
                return "redirect:/registration";
            }
            if(product!=null){
//                Cart existingCart = cartRepository.findByUserAndProduct(currentUser,product);
                Cart existingCart = productService.getFindCartByUserAndProduct(currentUser,product);
                if(existingCart!=null){
                    existingCart.setCount(existingCart.getCount()+1);
                    cartRepository.save(existingCart);
                } else{
                    Cart cart = new Cart();
                    cart.setProduct(product);
                    cart.setUser(currentUser);
                    cart.setCount(1);
                    cartRepository.save(cart);
                }
            }



            return  "redirect:/products";
        }

        @GetMapping(path = "/update")
        public String updateProductController(Model model){
//            List<Product> productList5 = productRepository.findAll();
            List<Product> productList5 = productService.getFindAllProduct();
            model.addAttribute("productList", productList5);
            return "data/update_product_page";
        }


        @PostMapping(path = "/create_product")
        public String formCreate(
                @RequestParam(name = "category_id") Category category,
                @RequestParam(name = "name") String name,
                @RequestParam(name = "price") String price

        ){
            Product product = new Product();
            product.setName(name);
            product.setCategory(category);
            product.setPrice(Integer.parseInt(price));
            productRepository.save(product);
            return "redirect:/products";
        }

    @PostMapping(path = "/update")
    public String productUpdate(
            @RequestParam(name = "product_id") Product product,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") String price

    ){
        product.setName(name);
        product.setPrice(Integer.parseInt(price));
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping(path = "/{productId}")
    public String productId(@PathVariable int productId,
                            Model model) {
//            Product product = productRepository.findById((long) productId).orElse(null);


            Product product = productService.getFindProductById((long) productId);
            boolean userHasComment = reviewService.checkIfUserHasComment((long) productId);
            if (userHasComment == false) {
                model.addAttribute("showCommentField", true);
            } else {
                model.addAttribute("showCommentField", false);
            }

            model.addAttribute("products",product);
//            List<Review> review = reviewRepository.findAllByProductId((long) productId);
            List<Review> review = productService.getFindReviewsByProductId((long) productId);
            model.addAttribute("reviews",review);
            model.addAttribute("avgRating",reviewService.getAvgRating(productId));

            return "data/product";
    }

    @PostMapping(path = "/{productId}")
    public String setReview(
            @PathVariable int productId,
            @RequestParam (name = "text") String text,
            @RequestParam (name = "rating") int rating,
            Model model
    ){
        boolean userHasComment = reviewService.checkIfUserHasComment((long) productId);
        if (userHasComment == false) {
            model.addAttribute("showCommentField", true);
        } else {
            model.addAttribute("showCommentField", false);
        }
       if(!text.isEmpty()){
           Review userReview = reviewService.setReview(productId,rating,text);
           reviewRepository.save(userReview);
       }

        return "redirect:/products/{productId}";
    }


}

