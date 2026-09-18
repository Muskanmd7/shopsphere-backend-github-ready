package com.shopsphere.shopsphere_backend.adminDashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dashboard {

    private Long totalUsers;

    private Long totalProducts;

    private Long totalCategories;

    private Long totalOrders;

    private Double totalRevenue;
}
