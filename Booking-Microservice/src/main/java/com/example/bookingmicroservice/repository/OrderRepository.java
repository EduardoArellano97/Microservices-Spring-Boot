package com.example.bookingmicroservice.repository;

import com.example.bookingmicroservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
