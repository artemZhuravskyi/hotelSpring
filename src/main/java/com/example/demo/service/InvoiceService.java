package com.example.demo.service;

import com.example.demo.DAO.InvoiceRepository;
import com.example.demo.DAO.OrderRepository;
import com.example.demo.model.Invoice;
import static com.example.demo.model.Invoice.Status.NOT_PAID;
import static com.example.demo.model.Invoice.Status.PAID;

import com.example.demo.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@AllArgsConstructor
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    public void createInvoice(Order order) {
        Invoice invoice = Invoice.builder()
                .order(order)
                .status(NOT_PAID)
                .build();
        invoiceRepository.save(invoice);
    }

    @Transactional
    public void payInvoice(Invoice invoice) {
        invoice.setStatus(PAID);
    }


}
