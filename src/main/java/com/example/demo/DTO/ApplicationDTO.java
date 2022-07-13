package com.example.demo.DTO;

import com.example.demo.model.enums.RoomClass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationDTO {

    private Long guestsNum;
    private Long lengthOfStay;
    private RoomClass roomClass;

}
