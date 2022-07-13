package com.example.demo.controller;

import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.model.Application;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

    @GetMapping("/application")
    public String enterApplicationPage() {
        return "applicationPage";
    }

    @PostMapping("/creat-application")
    public String createApplication(ApplicationDTO applicationDTO,
                                    Authentication authentication) {

        User currentUser = (User) authentication.getPrincipal();
        applicationService.createApplication(applicationDTO, currentUser);
        return "redirect:/main";
    }

    @GetMapping("/application-managing")
    public String applicationManaging() {

        return "/applicationManagingPage";
    }

    @GetMapping("/daYa")
    public String daYa() {

        applicationService.sendApplicationRequest(1L, new Application());
        return "/applicationManagingPage";
    }

    @PostMapping("/application-request/{id}")
    public String sendApplicationRequest(@PathVariable("id") Long id,
                                         @RequestBody Application application) {
        applicationService.sendApplicationRequest(id, application);
        return "redirect:/application-managing";
    }

    @GetMapping("/all-requests")
    public String showAllApplications(Authentication authentication,
                                      Model model) {
        User currentUser = (User) authentication.getPrincipal();
        model.addAttribute("allRequests", applicationService.showAllRequests(currentUser));
        return "/requestsPage";
    }



}
