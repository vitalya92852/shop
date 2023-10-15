package com.example.shop.service;

import com.example.shop.entity.*;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.OrderProductRepository;
import com.example.shop.repository.OrderRepository;
import com.example.shop.repository.StatusRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final CartRepository cartRepository;

    private final UserService userService;

    private final OrderRepository orderRepository;

    private final StatusRepository statusRepository;

    private final OrderProductRepository orderProductRepository;

    public OrderService(CartRepository cartRepository,
                        UserService userService,
                        OrderRepository orderRepository,
                        StatusRepository statusRepository,
                        OrderProductRepository orderProductRepository) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.statusRepository = statusRepository;
        this.orderProductRepository = orderProductRepository;
    }

    public Order addOrder(){
        User currentUser = userService.getCurrentUser();
        Order order = new Order();
        order.setUser(currentUser);
        order.setDate(LocalDateTime.now());
        order.setAddress("локомоко 1/1");
        Status status = statusRepository.findById((long)2).orElse(null);
        order.setStatus(status);
        return order;
    }

    public void addOrderProduct(Order order){
        User currentUser = userService.getCurrentUser();
        List<Cart> cartList = cartRepository.findAllByUserId(currentUser.getId());
        for(Cart cart:cartList){
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(cart.getProduct());
            orderProduct.setOrder(order);
            orderProduct.setCount(cart.getCount());
            orderProductRepository.save(orderProduct);
        }
    }

    public List<Order> getfindAllByUserId(Long userId){
        return orderRepository.findAllByUserId(userId);
    }

    public List<OrderProduct> findAllByOrderIds(List<Long> orderIds) {
        return orderProductRepository.findAllByOrderIdIn(orderIds);
    }

    public List<Order> getFindAllOrdersByStatusId(Long statusId) {
        return orderRepository.findAllByStatusId(statusId);
    }

    public Order getFindOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Status getFindStatusById(Long statusId) {
        return statusRepository.findById(statusId).orElse(null);
    }


}
