package com.example.demo.model;

import com.example.demo.model.enums.RoomClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
//    @JsonIgnore
    @ManyToMany
    private List<Room> room;
//    @JsonIgnore
    @ManyToOne
    private User client;

    private LocalDate firstDate;
    private LocalDate lastDate;
    @Enumerated(EnumType.STRING)
    private RoomClass roomClass;

}
