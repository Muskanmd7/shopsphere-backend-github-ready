package com.shopsphere.shopsphere_backend.cart.CartService;

//package com.shopsphere.shopsphere_backend.cart;

import com.shopsphere.shopsphere_backend.cart.Cart;
import com.shopsphere.shopsphere_backend.cart.cartRepo.CartRepo;
import com.shopsphere.shopsphere_backend.exception.ResourcenotFound;
import com.shopsphere.shopsphere_backend.products.model.Product;
import com.shopsphere.shopsphere_backend.products.repository.ProductRepository;
import com.shopsphere.shopsphere_backend.user.model.User;
import com.shopsphere.shopsphere_backend.user.Repository.userRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

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

    public Cart addToCart(Integer productId, Integer quantity, HttpServletRequest request) {

        User user = getLoggedInUser(request);

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourcenotFound("Product Not Found"));

        Cart existingCart = cartRepo.findByUserAndProduct(user, product).orElse(null);

        if (existingCart != null) {

            existingCart.setQuantity(existingCart.getQuantity() + quantity);

            return cartRepo.save(existingCart);
        }

        Cart cart = new Cart();

        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(quantity);

        return cartRepo.save(cart);
    }

    public List<Cart> getMyCart(HttpServletRequest request) {

        User user = getLoggedInUser(request);

        return cartRepo.findByUser(user);
    }

    public Cart updateQuantity(Long cartId, Integer quantity) {

        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourcenotFound("Cart Item Not Found"));

        cart.setQuantity(quantity);

        return cartRepo.save(cart);
    }

    public void deleteCartItem(Long cartId) {

        cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourcenotFound("Cart Item Not Found"));

        cartRepo.deleteById(cartId);
    }

    public void clearCart(HttpServletRequest request) {

        User user = getLoggedInUser(request);

        List<Cart> carts = cartRepo.findByUser(user);

        cartRepo.deleteAll(carts);
    }

}