package com.example.demo.DAO;

import com.example.demo.model.Application;
import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select count(o.id) from Room r join Order o on r.id = o.room.id where r.id = ?1 and" +
            "    (o.firstDate <= ?2 and o.lastDate >= ?3)" +
            "    GROUP BY r.id")
    Optional<Integer> countIntersectionDateQuantity(Long roomId, LocalDate lastDate, LocalDate firstDate);


}
