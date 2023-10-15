package com.example.shop.controller;

import com.example.shop.entity.Order;
import com.example.shop.entity.Review;
import com.example.shop.entity.Status;
import com.example.shop.repository.ReviewRepository;
import com.example.shop.service.ReviewService;
import jakarta.servlet.http.PushBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/check_user_review")
public class CheckUserReview {

    private final ReviewRepository reviewRepository;

    private final ReviewService reviewService;

    public CheckUserReview(ReviewRepository reviewRepository,
                           ReviewService reviewService) {
        this.reviewRepository = reviewRepository;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String checkReviewList(
            Model model
    ){
//        List<Review> reviewList = reviewRepository.findAllByPublicationFalse();
        List<Review> reviewList = reviewService.getFindAllUnpublishedReviews();
        model.addAttribute("reviewList",reviewList);

        return "data/check_review";
    }

    @PostMapping
    public String confirmUserReview(
            Model model,
            @RequestParam(name = "confirm", required = false) String confirm,
            @RequestParam(name = "reject", required = false) String reject,
            @RequestParam(name = "review_confirm", required = false) Long reviewConfirm,
            @RequestParam(name = "review_reject", required = false) Long reviewReject
    )
    {
        if ("confirm".equals(confirm)) {
//            Review review = reviewRepository.findById(orderConfirm).orElse(null);
            Review review = reviewService.getFindReviewById(reviewConfirm);
            if (review != null) {
                review.setPublication(true);
                reviewRepository.save(review);
            }
        } else if ("reject".equals(reject)) {
//            Review review = reviewRepository.findById(reviewReject).orElse(null);
            Review review = reviewService.getFindReviewById(reviewReject);
            if (review != null) {
                reviewRepository.delete(review);
            }
        }
//        List<Review> reviewList = reviewRepository.findAllByPublicationFalse();
        List<Review> reviewList = reviewService.getFindAllUnpublishedReviews();
        model.addAttribute("reviewList",reviewList);

        return "data/check_review";
    }
}
