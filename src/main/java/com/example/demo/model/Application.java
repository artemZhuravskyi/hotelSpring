package com.example.demo.model;

import com.example.demo.model.enums.RoomClass;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = "applications")
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<Room> room;
    @ManyToOne

    private User client;

    private LocalDate firstDate;
    private LocalDate lastDate;
    @Enumerated(EnumType.STRING)
    private RoomClass roomClass;
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ON_MANAGER_REVIEW,
        ON_CLIENT_REVIEW
    }
}
