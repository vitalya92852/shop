package com.example.shop.service;

import com.example.shop.entity.Product;
import com.example.shop.entity.Review;
import com.example.shop.entity.User;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final ProductRepository productRepository;


    public ReviewService(ReviewRepository reviewRepository,
                         UserService userService,
                         ProductRepository productRepository
                         ) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.productRepository = productRepository;
    }

    public int getAvgRating(int productId) {
        List<Review> reviews = reviewRepository.findAllByProductId((long) productId);
        if (reviews.isEmpty()) return 0;
        int count = 0;
        int avgRating = 0;
        for (Review review : reviews) {
            if(review.getPublication()) {
                avgRating += review.getRating();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        } else {
            return avgRating / count;
        }
    }

    public Review setReview(int productId, int rating, String text) {
        User currentUser = userService.getCurrentUser();
        Review review = new Review();
        Product product = productRepository.findById((long) productId).orElse(null);
        review.setUser(currentUser);
        review.setDate(new Date());
        review.setProduct(product);
        review.setRating(rating);
        review.setText(text);
        review.setPublication(false);
        return review;
    }

    public boolean checkIfUserHasComment(Long productId) {
        boolean userHasComment = false;
        User currentUser = userService.getCurrentUser();
        if(currentUser==null){
            return true;
        }
        List<Review> reviewList = reviewRepository.findAllByProductIdAndUserId(productId, currentUser.getId());
        if (!reviewList.isEmpty()) {
            userHasComment = true;
        }

        return userHasComment;

    }

    public List<Review> getFindAllUnpublishedReviews() {
        return reviewRepository.findAllByPublicationFalse();
    }


    public Review getFindReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

}


