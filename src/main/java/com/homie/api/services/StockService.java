package com.homie.api.services;

import com.homie.api.models.Stock;
import com.homie.api.data.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }
}