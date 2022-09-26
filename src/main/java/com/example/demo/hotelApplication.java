package com.example.demo;

import com.example.demo.DAO.ApplicationRepository;
import com.example.demo.DAO.RoomRepository;
import com.example.demo.DAO.UserRepository;
import com.example.demo.controller.OrdersController;
import com.example.demo.model.User;
import com.example.demo.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableScheduling
@SpringBootApplication
public class hotelApplication {
    @Autowired
    OrdersController ordersController;

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
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

			User user = User.builder()
                    .id(1L)
					.firstName("Artem")
					.lastName("Zhuravskyi")
					.email("a@a.com")
					.password(bCryptPasswordEncoder.encode("12345678"))
					.role(Role.ROLE_MANAGER)
					.build();
            userRepository.save(user);

        });
    }


}
