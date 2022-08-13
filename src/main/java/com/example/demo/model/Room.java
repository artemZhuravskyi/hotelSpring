package com.example.demo.model;

import com.example.demo.model.enums.RoomClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "rooms")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private List<Order> orders;
//    @JsonIgnore
    @OneToOne(mappedBy = "room", cascade = CascadeType.REMOVE)
    private Image images;
//    @JsonIgnore
    @ManyToMany(mappedBy = "room")
    private List<Application> application;

    private Long personNumber;
    @Enumerated(EnumType.STRING)
    private RoomClass roomClass;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Long price;

    public enum Status {
        FREE,
        BOOKED,
        BUSY,
        UNAVAILABLE
    }

}
