package com.example.demo.DAO;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByClient(User client);
    Order findByClientAndId(User client, Long id);
    List<Order> findAllByStatus(Order.Status status);
}
