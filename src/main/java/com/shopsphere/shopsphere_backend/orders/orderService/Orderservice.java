package com.shopsphere.shopsphere_backend.orders.orderService;

import com.shopsphere.shopsphere_backend.orders.Orders;
import com.shopsphere.shopsphere_backend.orders.ordersRepo.OrderRepo;
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
import com.shopsphere.shopsphere_backend.exception.ResourcenotFound;

@Service
public class Orderservice {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private userRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    private User getLoggedInUser(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourcenotFound("User Not Found"));
    }

    public Orders placeOrder(Integer productId,
                             Integer quantity,
                             HttpServletRequest request) {

        User user = getLoggedInUser(request);

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourcenotFound("Product Not Found"));

        Orders order = new Orders();

        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("PLACED");

        order.setTotalPrice(product.getProductPrice() * quantity);

        return orderRepo.save(order);

    }

    public List<Orders> getMyOrders(HttpServletRequest request) {

        User user = getLoggedInUser(request);

        return orderRepo.findByUser(user);

    }

    public List<Orders> getAllOrders() {

        return orderRepo.findAll();

    }

    public Orders updateStatus(Long id, String status) {

        Orders order = orderRepo.findById(id)
                .orElseThrow(() -> new ResourcenotFound("Order Not Found"));

        order.setOrderStatus(status);

        return orderRepo.save(order);

    }

    public void deleteOrder(Long id) {

        orderRepo.findById(id)
                .orElseThrow(() -> new ResourcenotFound("Order Not Found"));

        orderRepo.deleteById(id);

    }

}