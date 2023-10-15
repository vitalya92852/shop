package com.example.shop.service;

import com.example.shop.entity.*;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class ProductService {
//    private static final Product[] PRODUCTS = new Product[]{
//            new Product("Смартфоны","Iphone 13",400000),
//            new Product("Смартфоны","Iphone 14",500000),
//            new Product("Телевизоры","LG",350000)
//    };

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final UserService userService;

    private final CategoryRepository categoryRepository;

    private final ReviewRepository reviewRepository;

    public ProductService(ProductRepository productRepository,
                          CartRepository cartRepository,
                          UserService userService,
                          CategoryRepository categoryRepository,
                          ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
        this.reviewRepository = reviewRepository;
    }

//    public List<Product> getProductList(){
//        return new ArrayList<>(List.of(PRODUCTS));
//    }


//    public List<Product> getCategory(String name,List<Product> products){
//        for (Product product : this.getProductList()) {
//            if (name.equals(product.getCategory())) {
//                products.add(product);
//            }
//        }
//        return products;
//    }
//
//    public List<Product> getAllCategory(List<Product> products){
//        for(Product product:this.getProductList()){
//            products.add(product);
//        }
//        return products;
//    }

    public List<Category> getFindAllCategory() {
        return categoryRepository.findAll();
    }

    public List<Product> getFindAllProduct() {
        return productRepository.findAll();
    }

    public Product getFindProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Cart getFindCartByUserAndProduct(User user, Product product) {
        return cartRepository.findByUserAndProduct(user, product);
    }

    public List<Review> getFindReviewsByProductId(Long productId) {
        return reviewRepository.findAllByProductId(productId);
    }




}
