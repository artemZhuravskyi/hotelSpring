package com.example.demo.controller;

import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.User;
import com.example.demo.model.enums.RoomClass;
import com.example.demo.service.ApplicationService;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class ApplicationController {

    @Autowired
    private final ApplicationService applicationService;
    private final OrderService orderService;

    // Manager application Page
    @GetMapping("/all-applications")
    public String enterManagerApplicationPage(Model model) {
        model.addAttribute("allApplications", applicationService.showAllApplications());
        return "allUsersApplications";
    }

    @PostMapping("/application-response")
    public String sendApplicationResponse(Long id) {

        ReservationDTO reservationDTO = applicationService.createReservationDTOFromApplication(id);
        orderService.orderRoom(reservationDTO, reservationDTO.getClient());

        return "redirect:/allUsersApplications";
    }

    //Client application Pages
    @GetMapping("/create-application")
    public String enterCreationApplicationPage() {
        return "/application";
    }

    @PostMapping("/create-application")
    public String createApplication(String date,
                                    RoomClass roomClass,
                                    Authentication authentication) {

        User currentUser = (User) authentication.getPrincipal();

        applicationService.createApplication(applicationService.createReservationDTO(date, roomClass), currentUser);
        return "redirect:/client-applications";
    }

    @GetMapping("/client-applications")
    public String enterClientApplications(Authentication authentication,
                                            Model model) {
        User client = (User) authentication.getPrincipal();
        model.addAttribute("clientApplications", applicationService.showClientApplications(client));
        return "/clientApplications";
    }

}
