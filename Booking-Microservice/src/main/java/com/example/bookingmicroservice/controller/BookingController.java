package com.example.bookingmicroservice.controller;

import com.example.bookingmicroservice.client.StockClient;
import com.example.bookingmicroservice.dto.OrderDTO;
import com.example.bookingmicroservice.entity.Order;
import com.example.bookingmicroservice.repository.OrderRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    StockClient stockClient;
    @PostMapping("/order")
    @HystrixCommand(fallbackMethod = "fallbackToStockService")
    public String saveOrder(@RequestBody OrderDTO orderDTO) {
        boolean inStock = orderDTO
                .getOrderItemList()
                .stream()
                .allMatch(orderItem -> stockClient.availableStock(orderItem.getCode()));


        if(inStock) {

            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItemList(orderDTO.getOrderItemList());
            orderRepository.save(order);


            return "Order Saved";
        }
        return "Order cannot be saved";
    }
    private String fallbackToStockService(){
        return "Something went wrong. Please try again :(";
    }



}
