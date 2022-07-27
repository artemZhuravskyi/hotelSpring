package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

    private OrderService orderService;

    @GetMapping("/orders")

    public String showOrders(Authentication authentication,
                                Model model,
                                @RequestParam("sortBy") Optional<String> sortBy,
                                @RequestParam("direction") Optional<String> direction,
                                @RequestParam("page") Optional<Integer> pageNo) {

        User currentUser = (User) authentication.getPrincipal();
        model.addAttribute("orders", orderService.showOrders(currentUser));

        int currentPage = pageNo.orElse(1);
        String sort = sortBy.orElse("id");
        String dir = "asc".equalsIgnoreCase(direction.orElse("asc")) ? "asc" : "desc";
        Page<Order> page = orderService.getPaginated(currentPage, sort, dir);
        List<Order> orders = page.getContent();
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sortField", sort);
        model.addAttribute("direction", dir);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("reverseDirection", dir.equals("asc") ? "desc" : "asc");
        return "orders";
    }
}
