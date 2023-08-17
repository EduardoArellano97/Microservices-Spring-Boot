package com.example.bookingmicroservice.dto;

import com.example.bookingmicroservice.entity.OrderItem;
import jakarta.servlet.http.HttpSessionActivationListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private List<OrderItem> orderItemList;


}
