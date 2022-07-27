package com.example.demo.controller;

import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.service.ApplicationService;
import com.example.demo.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
public class ApplicationController {

    @Autowired
    private final ApplicationService applicationService;
    private final RoomService roomService;

    // Manager application Page
    @GetMapping("/all-applications")
    public String enterManagerApplicationPage(Model model) {
        model.addAttribute("allApplications", applicationService.showAllApplications());
        return "allUsersApplications";
    }

    @PostMapping("/application-response")
    public String sendApplicationResponse(Long id) {

        ReservationDTO reservationDTO = applicationService.createReservationDTOFromApplication(id);
        roomService.bookRoom(reservationDTO, reservationDTO.getClient());

        return "redirect:/allUsersApplications";
    }

    //Client application Pages
    @GetMapping("/create-application")
    public String enterApplicationPage() {
        return "/applicationPage";
    }

    @PostMapping("/create-application")
    public String createApplication(ReservationDTO reservationDTO,
                                    Authentication authentication) {

        User currentUser = (User) authentication.getPrincipal();
        applicationService.createApplication(reservationDTO, currentUser);
        return "redirect:/main";
    }

    @GetMapping("/applications")
    public String enterAllClientApplications(Authentication authentication,
                                            Model model) {
        User client = (User) authentication.getPrincipal();
        model.addAttribute("allClientApplications", applicationService.showAllClientApplications(client));
        return "/clientApplications";
    }


}
