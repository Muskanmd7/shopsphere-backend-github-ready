package com.shopsphere.shopsphere_backend.orders.orderController;

import com.shopsphere.shopsphere_backend.orders.Orders;
import com.shopsphere.shopsphere_backend.orders.orderService.Orderservice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.shopsphere.shopsphere_backend.orders.orderService.Orderservice;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private Orderservice orderService;

    @PostMapping("/place/{productId}")
    public Orders placeOrder(@PathVariable Integer productId,
                             @Valid @RequestParam Integer quantity,
                             HttpServletRequest request){

        return orderService.placeOrder(productId,quantity,request);

    }

    @GetMapping
    public List<Orders> getMyOrders(HttpServletRequest request){

        return orderService.getMyOrders(request);

    }

    @GetMapping("/all")
    public List<Orders> getAllOrders(){

        return orderService.getAllOrders();

    }

    @PutMapping("/{id}")
    public Orders updateStatus(@PathVariable Long id,
                               @Valid @RequestParam String status){

        return orderService.updateStatus(id,status);

    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){

        orderService.deleteOrder(id);

    }

}