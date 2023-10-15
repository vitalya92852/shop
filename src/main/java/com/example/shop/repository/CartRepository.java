package com.example.shop.repository;

import com.example.shop.entity.Cart;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import com.example.shop.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUserAndProduct(User user, Product product);

    List<Cart> findAllByProductId(long id);

    List<Cart> deleteAllByProductAndUser(Product product,User user);

    List<Cart> findAllByUserId(Long userId);

    List<Cart> findAllByProductIdAndUserId(Long productId,Long userId);

    List<Cart> deleteAllByUserId(Long id);



}
