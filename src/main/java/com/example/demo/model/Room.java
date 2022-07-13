package com.example.demo.model;

import com.example.demo.model.enums.RoomClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "rooms")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "room")
    private List<Order> orders;

    private Long personNumber;
    private RoomClass roomClass;
//    @Enumerated(EnumType.STRING)
    private Status status;

    enum Status {
        FREE,
        BOOKED,
        BUSY,
        UNAVAILABLE
    }

}
