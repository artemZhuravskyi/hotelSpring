package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "orders")
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Room room;
    @ManyToOne
    private User client;
    @OneToOne(mappedBy = "order")
    private Invoice invoice;

    private LocalDate firstDate;
    private LocalDate lastDate;
    private LocalDate creationDate;
    private Status status;

    public enum Status {
        PAID,
        NOT_PAID
    }

}
