package com.example.demo.DTO;


import com.example.demo.model.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Room room;
    private LocalDate date;
    private Long lengthOfStay;

}
