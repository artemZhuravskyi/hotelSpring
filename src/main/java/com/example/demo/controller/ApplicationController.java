package com.example.demo.controller;

import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.User;
import com.example.demo.model.enums.RoomClass;
import com.example.demo.service.ApplicationService;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
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
    final static Logger logger = Logger.getLogger(OrderService.class);
    // Manager application Page
    @GetMapping("/all-applications")
    public String enterManagerApplicationPage(Model model) {
        model.addAttribute("allApplications", applicationService.showAllApplications());
        logger.info("added attribute allApplications");
        return "allUsersApplications";
    }

    @PostMapping("/application-response")
    public String sendApplicationResponse(Long roomId, Long applicationId) {

        ReservationDTO reservationDTO = applicationService.createReservationDTOFromApplication(roomId, applicationId);
        orderService.orderRoom(reservationDTO, reservationDTO.getClient());
        logger.info("ordered Room");
        return "redirect:/all-applications";
    }

    //Client application Pages
    @GetMapping("/create-application")
    public String enterCreationApplicationPage() {
        return "/application";
    }

    @PostMapping("/create-application")
    public String createApplication(String date,
                                    RoomClass roomClass,
                                    Authentication authentication,
                                    Model model) {

        User currentUser = (User) authentication.getPrincipal();
        model.addAttribute("asd", null);
        applicationService.createApplication(applicationService.createReservationDTO(date, roomClass), currentUser);
        logger.info("created Application");
        return "redirect:/client-applications";
    }

    @GetMapping("/client-applications")
    public String enterClientApplications(Authentication authentication,
                                            Model model) {
        User client = (User) authentication.getPrincipal();
        model.addAttribute("clientApplications", applicationService.showClientApplications(client));
        logger.info("added attribute clientApplications");
        return "/clientApplications";
    }

}
