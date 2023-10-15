package com.example.shop.repository;

import com.example.shop.entity.Order;
import com.example.shop.entity.OrderProduct;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
    List<OrderProduct> findAllByOrderIdIn(List<Long> orderIds);
}
