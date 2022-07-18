package com.example.demo.controller;

import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/all-rooms")
    public String showAllRooms(Model model,
                               @RequestParam("sortBy") Optional<String> sortBy,
                               @RequestParam("direction") Optional<String> direction,
                               @RequestParam("page") Optional<Integer> pageNo) {

        int currentPage = pageNo.orElse(1);
        String sort = sortBy.orElse("id");
        String dir = "asc".equalsIgnoreCase(direction.orElse("asc")) ? "asc" : "desc";
        Page<Order> page = roomService.getPaginated(currentPage, sort, dir);
        List<Order> orders = page.getContent();
        model.addAttribute("rooms", roomService.showAllRooms());
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sortField", sort);
        model.addAttribute("direction", dir);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("reverseDirection", dir.equals("asc") ? "desc" : "asc");

        return "/allRooms";
    }

    @GetMapping("/room-{id}")
    public String showRoom(Model model,
                                @PathVariable("id") Long id) {
        model.addAttribute("roomId", roomService.showRoom(id));
        return "/roomId";
    }

    @PostMapping("/book-room")
    public String bookRoom(@RequestBody ReservationDTO reservationDTO,
                           Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        roomService.bookRoom(reservationDTO, currentUser);
        return "/succeededPage";
    }
}
