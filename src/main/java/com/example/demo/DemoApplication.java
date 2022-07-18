package com.example.demo;

import com.example.demo.DAO.OrderRepository;
import com.example.demo.DAO.UserRepository;
import com.example.demo.controller.OrderController;
import com.example.demo.model.Order;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@EnableScheduling
@SpringBootApplication
public class DemoApplication {
    @Autowired
    OrderController orderController;

    @Autowired
    OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @Bean
    public CommandLineRunner runApplication(UserRepository userRepository) {
        return (args -> {
            System.out.println("generate");
            System.out.println("generate1");
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
        });
    }


}
