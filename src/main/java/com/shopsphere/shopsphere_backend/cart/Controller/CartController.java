package com.shopsphere.shopsphere_backend.cart.Controller;

 import jakarta.servlet.http.HttpServletRequest;
 import com.shopsphere.shopsphere_backend.cart.Cart;
 import com.shopsphere.shopsphere_backend.cart.CartService.CartService;
 import jakarta.servlet.http.HttpServletRequest;
 import jakarta.validation.Valid;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{productId}")
    public Cart addToCart(@PathVariable Integer productId,
                          @Valid @RequestParam Integer quantity,
                          HttpServletRequest request) {

        return cartService.addToCart(productId, quantity, request);
    }

    @GetMapping
    public List<Cart> getMyCart(HttpServletRequest request) {

        return cartService.getMyCart(request);
    }

    @PutMapping("/{cartId}")
    public Cart updateQuantity(@PathVariable Long cartId,
                               @Valid @RequestParam Integer quantity) {

        return cartService.updateQuantity(cartId, quantity);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCartItem(@PathVariable Long cartId) {

        cartService.deleteCartItem(cartId);
    }

    @DeleteMapping("/clear")
    public void clearCart(HttpServletRequest request) {

        cartService.clearCart(request);
    }
}