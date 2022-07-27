package com.example.demo;

import com.example.demo.DAO.ApplicationRepository;
import com.example.demo.DAO.OrderRepository;
import com.example.demo.DAO.RoomRepository;
import com.example.demo.DAO.UserRepository;
import com.example.demo.controller.OrderController;
import com.example.demo.model.Application;
import com.example.demo.model.Order;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.model.enums.Role;
import com.example.demo.model.enums.RoomClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@SpringBootApplication
public class hotelApplication {
    @Autowired
    OrderController orderController;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    public static void main(String[] args) {
        SpringApplication.run(hotelApplication.class, args);
    }

    @Bean
    public CommandLineRunner runApplication(UserRepository userRepository) {
        return (args -> {
            System.out.println("generate");
            System.out.println("generate1");
//            List<Order> order = orderRepository.findAll();
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

			User user = User.builder()
					.firstName("Artem")
					.lastName("Zhuravskyi")
					.email("a@a.com")
					.password(bCryptPasswordEncoder.encode("a"))
					.role(Role.ROLE_MANAGER)
					.build();
            userRepository.save(user);

            Application application = applicationRepository.findById(1L).get();
            Room room1 = roomRepository.findById(1L).get();
            Room room2 = roomRepository.findById(3L).get();
            Room room3 = roomRepository.findById(5L).get();
            application.setRoom(List.of(room1, room2, room3));
            applicationRepository.save(application);

            Application application2 = applicationRepository.findById(2L).get();
            Room room4 = roomRepository.findById(2L).get();
            Room room5 = roomRepository.findById(4L).get();
            Room room6 = roomRepository.findById(5L).get();
            application2.setRoom(List.of(room4, room5, room6));
            applicationRepository.save(application2);

//            room.setApplication(List.of(application));
//            roomRepository.save(room);



        });
    }


}
