package com.example.demo.DTO;

import com.example.demo.model.enums.RoomClass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ApplicationDTO {

    private LocalDate firstDate;
    private LocalDate lastDate;
    private RoomClass roomClass;
}
