package com.example.demo.controller;

import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final OrderService orderService;

    @GetMapping("/all-rooms")
    public String showAllRooms(Model model) {

        model.addAttribute("rooms", roomService.showAllRooms());



        return "/allRooms";
    }

    @GetMapping("/info-room-{id}")
    public String showRoom(Model model,
                                @PathVariable("id") Long id) {
        model.addAttribute("room", roomService.showRoom(id));
        return "/roomInfo";
    }


}
