package com.example.shop.repository;

import com.example.shop.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByProductId(Long id);
    List<Review> findAllByProductIdAndUserId(Long productId,Long userId);
    List<Review> findAllByPublicationFalse();
}
