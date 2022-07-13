package com.example.demo.service;


import com.example.demo.DAO.OrderRepository;
import com.example.demo.DAO.RoomRepository;
import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.Order;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.example.demo.model.Order.Status.NOT_PAID;

@AllArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final OrderRepository orderRepository;
    private final InvoiceService invoiceService;
    public final static int PAGE_SIZE = 10;

    public Room showRoom(Long id) {
        return roomRepository.findById(id).get();
    }

    public List<Room> showAllRooms() {
        return roomRepository.findAll();
    }

    public void bookRoom(ReservationDTO reservationDTO, User currentUser) {

        if (!isReservationDateValid(reservationDTO)) {
            return;
        }

        Room room = roomRepository.findById(reservationDTO.getRoomId()).get();
        Order order = Order.builder()
                .room(room)
                .client(currentUser)
                .firstDate(reservationDTO.getFirstDate())
                .lastDate(reservationDTO.getLastDate())
                .creationDate(LocalDate.now())
                .status(NOT_PAID)
                .build();
        invoiceService.createInvoice(order);
        orderRepository.save(order);

    }

    public boolean isReservationDateValid(ReservationDTO reservationDTO) {

        Integer counted = roomRepository.countIntersectionDateQuantity(reservationDTO.getRoomId(), reservationDTO.getFirstDate(), reservationDTO.getLastDate());
        return counted > 0;
    }

    public Page<Order> getPaginated(int pageNo, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, PAGE_SIZE, sort);
        return orderRepository.findAll(pageable);
    }



}
