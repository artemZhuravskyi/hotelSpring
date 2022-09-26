package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.exception.EmailException;
import com.example.demo.exception.PasswordException;
import com.example.demo.exception.UserException;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;
    final static Logger logger = Logger.getLogger(RegistrationController.class);

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String showRegistrationPage(@ModelAttribute("user") UserDTO userDTO,
                                       Model model) {

        model.addAttribute("exception", "");

        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(
            @ModelAttribute("user") @Valid UserDTO userDTO,
            BindingResult bindingResult,
            Model model) {

//        if (bindingResult.hasErrors()) {
//            System.out.println("bindRes");
//            return "registration";
//        }
        try {
            userService.register(userDTO);
        } catch (EmailException e) {
            model.addAttribute("emailException", userDTO.getEmail());
            logger.info(e);
            return "registration";
        } catch (PasswordException e) {
            model.addAttribute("passwordException", userDTO.getPassword());
            logger.info(e);
            return "registration";
        }

        return "redirect:/login";
    }
}
