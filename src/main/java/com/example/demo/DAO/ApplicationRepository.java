package com.example.demo.DAO;

import com.example.demo.model.Application;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Dictionary;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByClient(User user);

    Application findByRoom(Room room);
}
