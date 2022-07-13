package com.example.demo.service;

import com.example.demo.DAO.OrderRepository;
import com.example.demo.model.Invoice;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

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

    @Transactional
    public void payOrder(Long id, User currentUser) {

        Order order = orderRepository.findByClientAndId(currentUser, id);

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
    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedRateTask() {
        System.out.println("Hello");
        List<Order> order = orderRepository.findAllByStatus(NOT_PAID);
        order.stream().filter(this::isDateExpired).forEach(this::withDrawPayment);
    }

    private boolean isDateExpired(Order order) {
        return LocalDate.now().isAfter(order.getCreationDate().plusDays(2));
    }



}
