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
    @Enumerated(EnumType.STRING)
    private RoomClass roomClass;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Long price;
    private String pathToImage;

    private enum Status {
        FREE,
        BOOKED,
        BUSY,
        UNAVAILABLE
    }

}
