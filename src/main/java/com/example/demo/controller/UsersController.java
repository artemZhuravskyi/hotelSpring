package com.example.demo.controller;

import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class UsersController {

    private OrderService orderService;
    private UserService userService;

}
