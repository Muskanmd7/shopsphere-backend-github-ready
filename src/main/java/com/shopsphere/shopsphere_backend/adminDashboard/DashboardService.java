package com.shopsphere.shopsphere_backend.adminDashboard;

import com.shopsphere.shopsphere_backend.category.categoryRepo.CategoryRepo;
import com.shopsphere.shopsphere_backend.orders.Orders;
import com.shopsphere.shopsphere_backend.orders.ordersRepo.OrderRepo;
import com.shopsphere.shopsphere_backend.products.repository.ProductRepository;
import com.shopsphere.shopsphere_backend.user.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private userRepo userRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private OrderRepo orderRepo;

    public Dashboard getDashboardStats(){

        Dashboard dashboard=new Dashboard();

        dashboard.setTotalUsers(userRepo.count());

        dashboard.setTotalProducts(productRepo.count());

        dashboard.setTotalCategories(categoryRepo.count());

        dashboard.setTotalOrders(orderRepo.count());

        Double revenue=orderRepo.findAll()
                .stream()
                .mapToDouble(Orders::getTotalPrice)
                .sum();

        dashboard.setTotalRevenue(revenue);

        return dashboard;

    }

}
