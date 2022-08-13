package com.example.demo.controller.rest;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderRestController {

    private OrderService orderService;

    @GetMapping("/list")
    public List<Order> getOrders(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return orderService.findOrdersByClient(currentUser);
    }

    @GetMapping()
    public List<Order> getOrdersWithPaginationAndSorting(Authentication authentication,
                                                         @RequestParam(required = false, defaultValue = "0") int pageNum,
                                                         @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                         @RequestParam(required = false, defaultValue = "id") String sorting) {
        User currentUser = (User) authentication.getPrincipal();
        return orderService.findOrdersWithPaginationAndSort(currentUser, pageNum, pageSize, sorting);
    }


}
