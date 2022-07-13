package com.example.demo;

import com.example.demo.DAO.UserRepository;
import com.example.demo.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner runApplication(UserRepository userRepository) {
//		return (args -> {
//			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//			List<User> users = new ArrayList<>();
//
//			User user = User.builder()
//					.firstName("Artem")
//					.lastName("Zhuravskyi")
//					.email("a")
//					.password(bCryptPasswordEncoder.encode("a"))
////					.role(Role.ROLE_USER)
//					.build();
//
//			users.add(user);
//			userRepository.saveAll(users);
//
//		});
//	}


}
