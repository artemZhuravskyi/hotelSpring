package com.example.demo.service;

import com.example.demo.DAO.ApplicationRepository;
import com.example.demo.DAO.RoomRepository;
import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.Application;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.model.enums.RoomClass;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.model.Room.Status.BOOKED;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final RoomRepository roomRepository;
    private final OrderService orderService;

    public void createApplication(ReservationDTO reservationDTO, User currentUser) {
        Application application = Application.builder()
                .client(currentUser)
                .firstDate(reservationDTO.getFirstDate())
                .lastDate(reservationDTO.getLastDate())
                .roomClass(reservationDTO.getRoomClass())
                .room(findApplicationRoomsByFilter(reservationDTO))
                .build();
        applicationRepository.save(application);

    }


    public ReservationDTO createReservationDTO(String date, RoomClass roomClass) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        String[] firstDateAndLastDate = orderService.splitDateToFirstDateAndLastDate(date);
        return ReservationDTO.builder()
                .firstDate(LocalDate.parse(firstDateAndLastDate[0], formatter))
                .lastDate(LocalDate.parse(firstDateAndLastDate[1], formatter))
                .roomClass(roomClass)
                .build();
    }

    public List<Application> showClientApplications(User currentUser) {
        return applicationRepository.findAllByClient(currentUser);
    }

    public List<Application> showAllApplications() {
        return applicationRepository.findAll();
    }


    public void updateApplication(Long applicationId) {
        Application currentApp = applicationRepository.findById(applicationId).get();
        List<Room> rooms = currentApp.getRoom();
        rooms.forEach(room -> room.setStatus(BOOKED));
        roomRepository.saveAll(rooms);
    }

    public ReservationDTO createReservationDTOFromApplication(Long roomId, Long applicationId) {
        updateApplication(applicationId);
        Room room = roomRepository.findById(roomId).get();
        Application currentApp = applicationRepository.findById(applicationId).get();
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .firstDate(currentApp.getFirstDate())
                .lastDate(currentApp.getLastDate())
                .roomClass(currentApp.getRoomClass())
                .roomId(room.getId())
                .client(currentApp.getClient())
                .build();
        applicationRepository.delete(currentApp);
        return reservationDTO;
    }

    private List<Room> findApplicationRoomsByFilter(ReservationDTO reservationDTO) {
        List<Room> rooms = roomRepository.findAll();
        rooms = rooms.stream().filter(x -> {
            if (x.getRoomClass().equals(reservationDTO.getRoomClass())) {
                reservationDTO.setRoomId(x.getId());
                return orderService.isReservationDateValid(reservationDTO);
            }
            return false;
        }).collect(Collectors.toList());
        return rooms;
    }

}
