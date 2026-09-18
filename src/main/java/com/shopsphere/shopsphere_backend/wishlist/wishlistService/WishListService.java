package com.shopsphere.shopsphere_backend.wishlist.wishlistService;

import com.shopsphere.shopsphere_backend.exception.DuplicateResourceException;
import com.shopsphere.shopsphere_backend.exception.ResourcenotFound;
import com.shopsphere.shopsphere_backend.products.model.Product;
import com.shopsphere.shopsphere_backend.products.repository.ProductRepository;
import com.shopsphere.shopsphere_backend.user.Repository.userRepo;
import com.shopsphere.shopsphere_backend.user.model.User;
import com.shopsphere.shopsphere_backend.wishlist.Wishlist;
import com.shopsphere.shopsphere_backend.wishlist.wishlistRepo.WishlistRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WishListService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private userRepo userRepo;

    @Autowired
    private WishlistRepo wishlistRepo;

    private User getLoggedInUser(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourcenotFound("User Not Found"));
    }

    public Wishlist addToWishlist(Integer productId,
                                  HttpServletRequest request) {

        User user = getLoggedInUser(request);

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourcenotFound("Product Not Found"));

        if (wishlistRepo.findByUserAndProduct(user, product).isPresent()) {

            throw new DuplicateResourceException("Product already in Wishlist");

        }

        Wishlist wishlist = new Wishlist();

        wishlist.setUser(user);

        wishlist.setProduct(product);

        return wishlistRepo.save(wishlist);

    }

    public List<Wishlist> getWishlist(HttpServletRequest request) {

        User user = getLoggedInUser(request);

        return wishlistRepo.findByUser(user);

    }

    public void removeProduct(Long wishlistId) {
        wishlistRepo.findById(wishlistId)
                .orElseThrow(() -> new ResourcenotFound("Wishlist Item Not Found"));

        wishlistRepo.deleteById(wishlistId);

    }
    public void removeByProduct(Integer productId, HttpServletRequest request) {

        User user = getLoggedInUser(request);

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourcenotFound("Product Not Found"));

        Wishlist wishlist = wishlistRepo.findByUserAndProduct(user, product)
                .orElseThrow(() -> new ResourcenotFound("Wishlist Item Not Found"));

        wishlistRepo.delete(wishlist);
    }

    public void clearWishlist(HttpServletRequest request) {

        User user = getLoggedInUser(request);

        wishlistRepo.deleteAll(
                wishlistRepo.findByUser(user)
        );

    }

}