package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.enums.Role;
import com.example.demo.service.OrderService;
import com.example.demo.service.RoomService;
import lombok.AllArgsConstructor;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class OrdersController {

    private OrderService orderService;
    private RoomService roomService;
    final static Logger logger = Logger.getLogger(OrderService.class);

    @PostMapping("/pay-order-{orderId}")
    public String payOrder(Authentication authentication,
                             @PathVariable Long orderId) {

        User currentUser = (User) authentication.getPrincipal();
        orderService.payOrder(orderId, currentUser);
        logger.info("payed Order");
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
                           Authentication authentication,
                            Model model) {
        User currentUser = (User) authentication.getPrincipal();
        if (currentUser.getRole() == Role.ROLE_MANAGER) {
            model.addAttribute("notUser", "");
            return "error";
        }
        orderService.orderRoomById(dateRange, roomId, currentUser);
        return "redirect:/orders";
    }

    @GetMapping("/order-room-{roomId}")
    public String showRoomById(@PathVariable("roomId") Long id,
                               Model model) {
        model.addAttribute("reservedDates", orderService.findAllReservedDates(orderService.showAllOrdersByRoomAndLastDayAfter(id)));
        model.addAttribute("room", roomService.showRoom(id));
        return "roomOrder";
    }
}
