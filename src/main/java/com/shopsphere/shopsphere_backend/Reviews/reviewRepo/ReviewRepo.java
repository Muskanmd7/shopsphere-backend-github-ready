package com.shopsphere.shopsphere_backend.Reviews.reviewRepo;

import com.shopsphere.shopsphere_backend.Reviews.Review;
import com.shopsphere.shopsphere_backend.products.model.Product;
import com.shopsphere.shopsphere_backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {

    List<Review> findByProduct(Product product);

    List<Review> findByUser(User user);

}
