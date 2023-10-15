package com.example.shop.controller;

import com.example.shop.entity.*;
import com.example.shop.repository.*;
import com.example.shop.service.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/products")
public class CartController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    public CartController(UserService userService,
                          UserRepository userRepository,
                          CartService cartService,
                          CartRepository cartRepository,
                          OrderRepository orderRepository,
                          OrderService orderService,
                          OrderProductRepository orderProductRepository,
                          ProductRepository productRepository,
                          ProductService productService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping(path = "/cart1")
    public String cart(
            Model model
    ){
        User currentUser = userService.getCurrentUser();
        List<Cart> cartList = cartService.getFindAllByUserId(currentUser.getId());
        model.addAttribute("cartList",cartList);
        model.addAttribute("totalCost",cartService.totalCost(cartList));
        return "data/cart1";
    }
    @PostMapping(path = "/order")
    public String order(Model model){
        User currentUser = userService.getCurrentUser();
        Order order = orderService.addOrder();
//        if(!cartRepository.findAllByUserId(currentUser.getId()).isEmpty()){
//            orderRepository.save(order);
//        }
        if(!cartService.getFindAllByUserId(currentUser.getId()).isEmpty()){
            orderRepository.save(order);
        }
        orderService.addOrderProduct(order);
//        List<Order> orderList = orderRepository.findAllByUserId(currentUser.getId());
        List<Order> orderList = orderService.getfindAllByUserId(currentUser.getId());
        List<Long> orderIds = orderList.stream()
                .map(Order::getId)
                .collect(Collectors.toList());
//        List<OrderProduct> orderProductList = orderProductRepository.findAllByOrderIdIn(orderIds);
        List<OrderProduct> orderProductList = orderService.findAllByOrderIds(orderIds);
//        List<Order> orders = orderRepository.findAllByUserId(currentUser.getId());
        List<Order> orders = orderService.getfindAllByUserId(currentUser.getId());
        model.addAttribute("order",orderProductList);
        model.addAttribute("orderList",orders);


        cartService.clearCart(currentUser);

        return "data/order_page";
    }

    @GetMapping(path = "/order")
    public String showOrderPage(Model model){
        User currentUser = userService.getCurrentUser();
//        List<Order> orderList = orderRepository.findAllByUserId(currentUser.getId());
        List<Order> orderList = orderService.getfindAllByUserId(currentUser.getId());
        List<Long> orderIds = orderList.stream()
                .map(Order::getId)
                .collect(Collectors.toList());
//        List<OrderProduct> orderProductList = orderProductRepository.findAllByOrderIdIn(orderIds);
        List<OrderProduct> orderProductList = orderService.findAllByOrderIds(orderIds);
//        List<Order> orders = orderRepository.findAllByUserId(currentUser.getId());
        List<Order> orders = orderService.getfindAllByUserId(currentUser.getId());
        model.addAttribute("order",orderProductList);
        model.addAttribute("orderList",orders);



        return "data/order_page";
    }

    @PostMapping(path = "/cart1")
    public String countPlusAndMinus(
            @RequestParam(name = "plusOneAction", required = false) String plusOneAction,
            @RequestParam(name = "minusOneAction", required = false) String minusOneAction,
            @RequestParam(name = "productId",required = false) Long productId,
            @RequestParam(name = "cartListConf", required = false) List<Cart> cartListConf,
            @RequestParam(name = "removeAction",required = false)String removeAction,
            @RequestParam(name = "clearAllAction",required = false)String clearAllAction
    ){
        if(productId!=null) {
            cartService.productCartMinusAndPlus(productId, plusOneAction, minusOneAction);
        }
        if(removeAction!=null) {
            if (removeAction.equals("remove")) {
                cartService.deleteProductFromCart(productId);
            }
        }
        if(clearAllAction!=null){
            if(clearAllAction.equals("clearAll")){
                cartService.deleteAllProductFromCart();
            }
        }
        if (cartListConf != null) {

            return "redirect:/products/order";
        }

        return "redirect:/products/cart1";
    }
}
