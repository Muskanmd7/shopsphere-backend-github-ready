package com.shopsphere.shopsphere_backend.Reviews.reviewController;

import com.shopsphere.shopsphere_backend.Reviews.Review;
import com.shopsphere.shopsphere_backend.Reviews.ReviewService.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{productId}")
    public Review addReview(@PathVariable Integer productId,
                            @Valid @RequestBody Review review,
                            HttpServletRequest request) {

        return reviewService.addReview(productId, review, request);
    }

    @GetMapping("/product/{productId}")
    public List<Review> getReviewsByProduct(@PathVariable Integer productId) {

        return reviewService.getReviewsByProduct(productId);
    }

    @GetMapping("/my")
    public List<Review> getMyReviews(HttpServletRequest request) {

        return reviewService.getMyReviews(request);
    }

    @PutMapping("/{reviewId}")
    public Review updateReview(@PathVariable Long reviewId,
                               @RequestBody Review review) {

        return reviewService.updateReview(reviewId, review);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {

        reviewService.deleteReview(reviewId);
    }

}
