package com.example.springboot_01.controller;

import com.example.springboot_01.model.BaseResponseModel;
import com.example.springboot_01.dto.stock.StockDto;
import com.example.springboot_01.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<BaseResponseModel> listStocks () {
        return stockService.listStock();
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseModel> getOneStock (@PathVariable("id") Long id){
        return stockService.getOneStock(id);
    }
    @PostMapping
    public ResponseEntity<BaseResponseModel> createStock(@RequestBody StockDto stock){
        return stockService.createStock(stock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseModel> updateStock(@PathVariable Long id, @RequestBody StockDto payload){
        return stockService.updateStock(id, payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseModel> deleteStock(@PathVariable Long id){
        return stockService.deleteStock(id);
    }
}
