package com.example.demo.controller;

import com.example.demo.DAO.OrderRepository;
import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.RoomService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRSaver;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;
    private RoomService roomService;

    @PostMapping("/pay-order/{orderId}")
    public String payOrder(Authentication authentication,
                             @PathVariable Long orderId) {

        User currentUser = (User) authentication.getPrincipal();
        orderService.payOrder(orderId, currentUser);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String getOrdersWithPaginationAndSorting(Authentication authentication,
                                                         Model model,
                                                         @RequestParam(required = false, defaultValue = "0") int pageNum,
                                                         @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                         @RequestParam(required = false, defaultValue = "id") String sorting) {
        User currentUser = (User) authentication.getPrincipal();
        model.addAttribute("orders", orderService.findOrdersWithPaginationAndSort(currentUser, pageNum, pageSize, sorting));
        return "orders";
    }

    @PostMapping("/order-room-{roomId}")
    public String orderRoom(String dateRange,
                           @PathVariable Long roomId,
                           Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();

        orderService.orderRoomById(dateRange, roomId, currentUser);
        return "redirect:/orders";
    }

    @GetMapping("/order-room-{roomId}")
    public String showRoomById(@PathVariable("roomId") Long id,
                               Model model) {
        model.addAttribute("reservedDates", orderService.ordersToDates(orderService.showAllOrdersByRoomAndLastDayAfter(id)));
        model.addAttribute("room", roomService.showRoom(id));
        return "roomOrder";
    }
}
