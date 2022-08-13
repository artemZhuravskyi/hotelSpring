package com.example.demo.DAO;

import com.example.demo.model.Order;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, PagingAndSortingRepository<Order, Long> {

    List<Order> findAllByClient(User client);
    List<Order> findAllByClient(User client, Pageable pageable);
    Order findByClientAndId(User client, Long id);
    List<Order> findAllByRoom(Room room);
    List<Order> findAllByRoomAndLastDateAfter(Room room, LocalDate localDate);

}
