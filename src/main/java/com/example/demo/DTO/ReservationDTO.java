package com.example.demo.DTO;

import com.example.demo.model.User;
import com.example.demo.model.enums.RoomClass;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReservationDTO {

    private LocalDate firstDate;
    private LocalDate lastDate;
    private RoomClass roomClass;
    private User client;
    private Long roomId;
}
