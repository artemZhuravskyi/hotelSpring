package com.example.demo.controller;

import com.example.demo.DAO.OrderRepository;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRSaver;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.InputStream;

@Controller
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping("/order")
    public String showOrders(Authentication authentication,
                             Model model){
        User currentUser = (User) authentication.getPrincipal();
        model.addAttribute("orders", orderService.showOrders(currentUser));
        return "/orders";
    }

    @PostMapping("/pay-order/{orderId}")
    public String payOrder(Authentication authentication,
                             @PathVariable("orderId") Long id) throws JRException {
        User currentUser = (User) authentication.getPrincipal();
        orderService.payOrder(id, currentUser);


        return "redirect:/orders";
    }

}
