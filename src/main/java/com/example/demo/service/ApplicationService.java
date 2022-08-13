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

import javax.transaction.Transactional;
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
    private final RoomService roomService;

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

    @Transactional
    public void updateApplication(Long id) {
        Room room = roomRepository.findById(id).get();
        Application currentApp = applicationRepository.findByRoom(room);
        List<Room> room1 = currentApp.getRoom();
        room1.forEach(x -> x.setStatus(BOOKED));
    }

    public ReservationDTO createReservationDTOFromApplication(Long id) {
        updateApplication(id);
        Room room = roomRepository.findById(id).get();
        Application currentApp = applicationRepository.findByRoom(room);
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
        rooms = rooms.stream().filter(x -> x.getRoomClass().equals(reservationDTO.getRoomClass())
                && orderService.isReservationDateValid(reservationDTO)).collect(Collectors.toList());
        return rooms;
    }

}
