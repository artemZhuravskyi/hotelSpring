package com.example.demo.service;

import com.example.demo.DAO.OrderRepository;
import com.example.demo.DAO.RoomRepository;
import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.Order;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.model.Order.Status.NOT_PAID;
import static com.example.demo.model.Order.Status.PAID;

@AllArgsConstructor
@Service
public class OrderService {

    private OrderRepository orderRepository;
    private InvoiceService invoiceService;
    private static final Logger logger = Logger.getLogger(OrderService.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private RoomRepository roomRepository;


    public List<Order> findOrdersByClient(User client) {
        return orderRepository.findAllByClient(client);
    }

    private byte[] bytePdfFile(Order order) {
        try {

            List<Order> allOrder = new ArrayList<>();
            allOrder.add(orderRepository.findById(order.getId()).get());

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

    protected boolean isReservationDateValid(ReservationDTO reservationDTO) {
        return roomRepository.countIntersectionDateQuantity(reservationDTO.getRoomId(),
                reservationDTO.getLastDate(), reservationDTO.getFirstDate()).isEmpty();

    }

    protected String[] splitDateToFirstDateAndLastDate(String date) {
        return date.split(" - ");
    }

    protected ReservationDTO createReservationDTO(String[] dates, Long id) {

        return ReservationDTO.builder()
                .firstDate(LocalDate.parse(dates[0], formatter))
                .lastDate(LocalDate.parse(dates[1], formatter))
                .roomId(id)
                .roomClass(roomRepository.findById(id).get().getRoomClass())
                .build();
    }

    public void orderRoomById(String dateRange, Long id, User currentUser) {
        String[] dates = splitDateToFirstDateAndLastDate(dateRange);
        ReservationDTO reservationDTO = createReservationDTO(dates, id);

        orderRoom(reservationDTO, currentUser);
    }

    public void orderRoom(ReservationDTO reservationDTO, User currentUser) {

        if (!isReservationDateValid(reservationDTO)) {
            return;
        }

        Room room = roomRepository.findById(reservationDTO.getRoomId()).get();

        createOrder(room, currentUser, reservationDTO);
    }

    protected void generateReport(Order order) {

        final String username = "hotelCaliforniaKyiv@gmail.com";
        final String password = "bpmbmgbemyypqrln";
        String recipientEmail = "artem.zhuravskyi22@gmail.com";

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
            DataSource dataSource = new ByteArrayDataSource(bytePdfFile(order), "application/pdf");
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
        order.setStatus(PAID);
        orderRepository.save(order);
        generateReport(order);
    }


    @Transactional
    public void withdrawPayment(Order order) {
        orderRepository.delete(order);
    }

    @Transactional
    @Scheduled(fixedDelay = 100000)
    public void scheduleFixedRateTask() {
        List<Order> order = orderRepository.findAllByStatus(NOT_PAID);
        order.stream().filter(this::isDateExpired).forEach(this::withdrawPayment);
    }

    private boolean isDateExpired(Order order) {
        return LocalDate.now().isAfter(order.getCreationDate().plusDays(2));
    }

    protected Long calculateOrderPrice(Room room, ReservationDTO reservationDTO) {

        long lengthOfStay = countLengthOfStay(reservationDTO);

        return switch (room.getRoomClass()) {
            case ECONOMY -> 100L * lengthOfStay;
            case STANDARD -> 200L * lengthOfStay;
            case JUNIOR_SUITE -> 300L * lengthOfStay;
            case SUITE -> 400L * lengthOfStay;
        };
    }

    private Long countLengthOfStay(ReservationDTO reservationDTO) {
        return ChronoUnit.DAYS.between(reservationDTO.getFirstDate(), reservationDTO.getLastDate()) + 1L;
    }

    protected void createOrder(Room room, User currentUser, ReservationDTO reservationDTO) {
        Order order = Order.builder()
                .room(room)
                .client(currentUser)
                .firstDate(reservationDTO.getFirstDate())
                .lastDate(reservationDTO.getLastDate())
                .creationDate(LocalDate.now())
                .price(calculateOrderPrice(room, reservationDTO))
                .status(NOT_PAID)
                .build();
        orderRepository.save(order);
    }

    public List<Long> findAllReservedDates(List<Order> orders) {
        ZoneId zoneId = ZoneId.systemDefault();
        logger.info("Orders: " + orders);
        return orders.stream()
                .map(x -> fromFDtoLD(x.getFirstDate(), x.getLastDate()))
                .flatMap(Collection::stream)
                .map(y -> y.atStartOfDay(zoneId).toInstant().toEpochMilli()).collect(Collectors.toList());
    }

    public List<Order> showAllOrdersByRoomAndLastDayAfter(Long id) {
        Room room = roomRepository.findById(id).get();
        return orderRepository.findAllByRoomAndLastDateAfter(room, LocalDate.now());
    }


    private List<LocalDate> fromFDtoLD(LocalDate firstDate, LocalDate lastDate) {
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate temp = firstDate;
        dateList.add(temp);
        while (!temp.equals(lastDate)) {
            temp = temp.plusDays(1);
            dateList.add(temp);
        }
        return dateList;
    }

    public List<Order> findOrdersWithPaginationAndSort(User client, int pageNum, int pageSize, String sorting) {
        return orderRepository.findAllByClient(client, PageRequest.of(pageNum, pageSize).withSort(Sort.by(sorting)));
    }

}
