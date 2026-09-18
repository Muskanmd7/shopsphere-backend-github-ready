package com.shopsphere.shopsphere_backend.wishlist.wishlistController;

import com.shopsphere.shopsphere_backend.wishlist.Wishlist;
import com.shopsphere.shopsphere_backend.wishlist.wishlistService.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishListService wishlistService;

    @PostMapping("/{productId}")
    public Wishlist addToWishlist(@PathVariable Integer productId,
                                  HttpServletRequest request){

        return wishlistService.addToWishlist(productId,request);

    }

    @GetMapping
    public List<Wishlist> getWishlist(HttpServletRequest request){

        return wishlistService.getWishlist(request);

    }

    @DeleteMapping("/{wishlistId}")
    public void removeProduct(@PathVariable Long wishlistId){

        wishlistService.removeProduct(wishlistId);

    }

    @DeleteMapping("/clear")
    public void clearWishlist(HttpServletRequest request){

        wishlistService.clearWishlist(request);

    }
    @DeleteMapping("/product/{productId}")
    public void removeByProduct(@PathVariable Integer productId,
                                HttpServletRequest request) {

        wishlistService.removeByProduct(productId, request);

    }

}
