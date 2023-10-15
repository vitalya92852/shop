package com.example.shop.controller;

import com.example.shop.entity.Order;
import com.example.shop.entity.Status;
import com.example.shop.repository.OrderRepository;
import com.example.shop.repository.StatusRepository;
import com.example.shop.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/check_user_order")
public class CheckUserOrder {

    private final OrderRepository orderRepository;
    private final StatusRepository statusRepository;

    private final OrderService orderService;

    public CheckUserOrder(OrderRepository orderRepository,
                          StatusRepository statusRepository,
                          OrderService orderService) {
        this.orderRepository = orderRepository;
        this.statusRepository = statusRepository;
        this.orderService = orderService;
    }

    @GetMapping
    public String checkOrder(Model model) {
        List<Order> orderList = orderService.getFindAllOrdersByStatusId((long) 2);
        model.addAttribute("orderList", orderList);
        return "data/check_order";
    }

    @PostMapping
    public String confirmOrder(@RequestParam(name = "confirm", required = false) String confirm,
                               @RequestParam(name = "reject", required = false) String reject,
                               @RequestParam(name = "order_confirm", required = false) Long orderConfirm,
                               @RequestParam(name = "order_reject", required = false) Long orderReject) {
        if ("confirm".equals(confirm)) {
//            Order order = orderRepository.findById(orderConfirm).orElse(null);
            Order order = orderService.getFindOrderById(orderConfirm);
            if (order != null) {
//                Status status = statusRepository.findById((long) 1).orElse(null);
                Status status = orderService.getFindStatusById((long) 1);
                if (status != null) {
                    order.setStatus(status);
                    orderRepository.save(order);
                }
            }
        } else if ("reject".equals(reject)) {
//            Order order = orderRepository.findById(orderReject).orElse(null);
            Order order = orderService.getFindOrderById(orderReject);
            if (order != null) {
//                Status status = statusRepository.findById((long) 3).orElse(null);
                Status status = orderService.getFindStatusById((long) 3);
                if (status != null) {
                    order.setStatus(status);
                    orderRepository.save(order);
                }
            }
        }
        return "redirect:/check_user_order";
    }
}

