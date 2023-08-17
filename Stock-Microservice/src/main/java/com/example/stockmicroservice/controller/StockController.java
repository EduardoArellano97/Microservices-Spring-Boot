package com.example.stockmicroservice.controller;

import com.example.stockmicroservice.entity.StockEntity;
import com.example.stockmicroservice.repository.StockRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    @Autowired
    private StockRepository stockRepository;
    @RequestMapping("/{code}")
    boolean availableStock(@PathVariable String code){
        Optional<StockEntity> stock= stockRepository.findByCode(code);
        stock.orElseThrow(()->new RuntimeException("product cannot be found"+code));
        return stock.get().getQuantity() >0;

    }

}
