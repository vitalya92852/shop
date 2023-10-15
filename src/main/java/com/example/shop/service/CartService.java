package com.example.shop.service;

import com.example.shop.entity.Cart;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    private final UserService userService;

    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       UserService userService,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productRepository = productRepository;
    }


    public int totalCost(List<Cart> cartList){
        int sum = 0;
        for(Cart cart:cartList){
            sum+= cart.getProduct().getPrice()*cart.getCount();
        }
        return sum;
    }

    public void productCartMinusAndPlus(long productId,String plusOneAction,String minusOneAction){
        if (plusOneAction != null) {
            List<Cart> carts = cartRepository.findAllByProductId(productId);
            if (!carts.isEmpty()) {
                for (Cart cart : carts) {
                    cart.setCount(cart.getCount() + 1);
                    cartRepository.save(cart);
                }
            }
        }
        else if (minusOneAction != null) {
            List<Cart> carts = cartRepository.findAllByProductId(productId);
            if (!carts.isEmpty()) {
                for (Cart cart : carts) {
                    cart.setCount(cart.getCount() - 1);
                    if (cart.getCount() <= 0) {
                        cartRepository.deleteById(cart.getId());
                    } else {
                        cartRepository.save(cart);
                    }
                }
            }
        }
    }

    public void clearCart(User user) {
        List<Cart> userCart = cartRepository.findAllByUserId(user.getId());
        cartRepository.deleteAll(userCart);
    }

    public void deleteProductFromCart(Long productId){
        User currentUser = userService.getCurrentUser();
        List<Cart> carts = cartRepository.findAllByProductIdAndUserId(productId,currentUser.getId());
        if(!carts.isEmpty()) {
            for (Cart cart : carts) {
                cartRepository.delete(cart);
            }
        }
    }

    public void deleteAllProductFromCart(){
        User currentUser = userService.getCurrentUser();
        List<Cart> carts = cartRepository.findAllByUserId(currentUser.getId());
        if(!carts.isEmpty()) {
            for (Cart cart : carts) {
                cartRepository.delete(cart);
            }
        }
    }

    public List<Cart> getFindAllByUserId(Long userId){
        return cartRepository.findAllByUserId(userId);
    }





}
