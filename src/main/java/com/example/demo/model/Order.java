package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @ManyToOne
    private Room room;
    @JsonIgnore
    @ManyToOne
    private User client;
    @JsonIgnore
    @OneToOne(mappedBy = "order")
    private Invoice invoice;

    private LocalDate firstDate;
    private LocalDate lastDate;
    private LocalDate creationDate;
    private Long price;
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PAID,
        NOT_PAID
    }



}
