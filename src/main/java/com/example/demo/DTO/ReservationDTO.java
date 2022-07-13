package com.example.demo.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDTO {

    private Long roomId;
    private LocalDate firstDate;
    private LocalDate lastDate;
}
