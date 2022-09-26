package com.example.demo.service;


import com.example.demo.DAO.ApplicationRepository;
import com.example.demo.DAO.RoomRepository;
import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public Room showRoom(Long id) {
        return roomRepository.findById(id).get();
    }

    public List<Room> showAllRooms() {
        return roomRepository.findAll();
    }






}
