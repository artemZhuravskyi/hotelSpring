package com.example.demo.service;

import com.example.demo.DAO.InvoiceRepository;
import com.example.demo.DAO.OrderRepository;
import com.example.demo.model.Invoice;

import com.example.demo.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@AllArgsConstructor
public class InvoiceService {

    InvoiceRepository invoiceRepository;
    public void createInvoice(Order order) {
        Invoice invoice = Invoice.builder()
                .order(order)
                .build();
        invoiceRepository.save(invoice);
        order.setInvoice(invoice);
    }




}
