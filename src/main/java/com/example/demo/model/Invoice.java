package com.example.demo.model;


import lombok.*;

import javax.persistence.*;

@Table(name = "invoices")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Order order;

    private Status status;

    public enum Status {
        NOT_PAID,
        PAID
    }

}
