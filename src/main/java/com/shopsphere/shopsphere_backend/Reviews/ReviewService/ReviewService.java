package com.shopsphere.shopsphere_backend.Reviews.ReviewService;

import com.shopsphere.shopsphere_backend.Reviews.Review;
import com.shopsphere.shopsphere_backend.Reviews.reviewRepo.ReviewRepo;
import com.shopsphere.shopsphere_backend.exception.ResourcenotFound;
import com.shopsphere.shopsphere_backend.products.model.Product;
import com.shopsphere.shopsphere_backend.products.repository.ProductRepository;
import com.shopsphere.shopsphere_backend.user.Repository.userRepo;
import com.shopsphere.shopsphere_backend.user.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private userRepo userRepo;

    private User getLoggedInUser(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourcenotFound("User Not Found"));
    }

    public Review addReview(Integer productId,
                            Review review,
                            HttpServletRequest request) {

        User user = getLoggedInUser(request);

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourcenotFound("Product Not Found"));

        review.setUser(user);
        review.setProduct(product);
        review.setReviewDate(LocalDateTime.now());

        return reviewRepo.save(review);
    }

    public List<Review> getReviewsByProduct(Integer productId) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourcenotFound("Product Not Found"));

        return reviewRepo.findByProduct(product);
    }

    public List<Review> getMyReviews(HttpServletRequest request) {

        User user = getLoggedInUser(request);

        return reviewRepo.findByUser(user);
    }

    public Review updateReview(Long reviewId,
                               Review updatedReview) {

        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new ResourcenotFound("Review Not Found"));

        review.setRating(updatedReview.getRating());
        review.setReview(updatedReview.getReview());

        return reviewRepo.save(review);
    }

    public void deleteReview(Long reviewId) {

        reviewRepo.findById(reviewId)
                .orElseThrow(() -> new ResourcenotFound("Review Not Found"));

        reviewRepo.deleteById(reviewId);
    }

}