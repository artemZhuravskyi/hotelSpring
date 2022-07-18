package com.example.demo.service;

import com.example.demo.DAO.OrderRepository;
import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.Invoice;
import com.example.demo.model.Order;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
import javax.transaction.Transactional;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.example.demo.model.Order.Status.NOT_PAID;
import static com.example.demo.model.Order.Status.PAID;

@AllArgsConstructor
@Service
public class OrderService {

    private OrderRepository orderRepository;
    private InvoiceService invoiceService;


    public List<Order> showOrders(User currentUser) {
        return orderRepository.findAllByClient(currentUser);
    }


    private byte[] bytePdfFile() {
        try {

            List<Order> allOrder = orderRepository.findAll();

            JasperReport jasperReport = JasperCompileManager
                    .compileReport("C:\\Users\\iisus\\Desktop\\demo\\src\\main\\resources\\templates\\jrxml\\invoice.jrxml");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allOrder);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,
                    dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException ignored) {
            System.out.println("error");
        }
        return null;
    }

    private void generateReport() {

        final String username = "hotelCaliforniaKyiv@gmail.com";
        final String password = "bpmbmgbemyypqrln";
        String recipientEmail = "rapturelilpeep@gmail.com";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytePdfFile(), "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(pdfBodyPart);
            pdfBodyPart.setFileName("invoice.pdf");

            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(username);
            InternetAddress iaRecipient = new InternetAddress(recipientEmail);

            //construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);

            //send off the email
            Transport.send(mimeMessage);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Transactional
    public void payOrder(Long id, User currentUser) {

        Order order = orderRepository.findByClientAndId(currentUser, id);
        invoiceService.createInvoice(order);

//        generateReport(currentUser,);

        if (isDateExpired(order)) {
            withDrawPayment(order);
        }

        Invoice invoice = order.getInvoice();
        order.setStatus(PAID);
        invoiceService.payInvoice(invoice);
    }


    @Transactional
    public void withDrawPayment(Order order) {
        orderRepository.delete(order);
    }

    @Transactional
    @Scheduled(fixedDelay = 100000)
    public void scheduleFixedRateTask() {
        System.out.println("Hello");
        List<Order> order = orderRepository.findAllByStatus(NOT_PAID);
        order.stream().filter(this::isDateExpired).forEach(this::withDrawPayment);
    }

    private boolean isDateExpired(Order order) {
        return LocalDate.now().isAfter(order.getCreationDate().plusDays(2));
    }

    private Long setOrderPrice(Room room, ReservationDTO reservationDTO) {

        long lengthOfStay = countLengthOfStay(reservationDTO);

        return switch (room.getRoomClass()) {
            case ECONOMY -> 100L * lengthOfStay;
            case STANDARD -> 200L * lengthOfStay;
            case JUNIOR_SUITE -> 300L * lengthOfStay;
            case SUITE -> 400L * lengthOfStay;
        };
    }

    private Long countLengthOfStay(ReservationDTO reservationDTO) {
        return ChronoUnit.DAYS.between(reservationDTO.getFirstDate(), reservationDTO.getLastDate());
    }

    protected void createOrder(Room room, User currentUser, ReservationDTO reservationDTO) {
        Order order = Order.builder()
                .room(room)
                .client(currentUser)
                .firstDate(reservationDTO.getFirstDate())
                .lastDate(reservationDTO.getLastDate())
                .creationDate(LocalDate.now())
                .price(setOrderPrice(room, reservationDTO))
                .status(NOT_PAID)
                .build();
        orderRepository.save(order);
    }


}
