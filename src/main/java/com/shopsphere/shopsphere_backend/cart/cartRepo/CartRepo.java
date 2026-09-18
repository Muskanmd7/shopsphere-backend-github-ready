package com.shopsphere.shopsphere_backend.cart.cartRepo;

import com.shopsphere.shopsphere_backend.cart.Cart;
import com.shopsphere.shopsphere_backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {

    List<Cart> findByUser(User user);

    Optional<Cart> findByUserAndProduct(User user,
                                        com.shopsphere.shopsphere_backend.products.model.Product product);
}
