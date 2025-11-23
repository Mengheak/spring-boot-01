package com.example.springboot_01.service;

import com.example.springboot_01.entity.Product;
import com.example.springboot_01.entity.Stock;
import com.example.springboot_01.mapper.StockMapper;
import com.example.springboot_01.model.ApiResponseModel;
import com.example.springboot_01.model.BaseResponseModel;
import com.example.springboot_01.dto.stock.StockDto;
import com.example.springboot_01.repository.ProductRepository;
import com.example.springboot_01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockMapper stockMapper;
    public ResponseEntity<BaseResponseModel> listStock() {
        List<Stock> stocks = stockRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get stocks successfully", stockMapper.toDtoList(stocks)));
    }

    public ResponseEntity<BaseResponseModel> getOneStock(Long id) {
        Optional<Stock> fetchedStock = stockRepository.findById(id);
        if(fetchedStock.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseModel("failed", "stock is not found"));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get one stock successfully", stockMapper.toDto(fetchedStock.get())));
    }

    public ResponseEntity<BaseResponseModel> createStock(StockDto payload) {


        Optional<Product> existingProduct = productRepository.findById(payload.getProductId());
        if(existingProduct.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseModel("failed", "product id has not been found"));
        }
        Optional<Stock> existingStock = stockRepository.findByProductId(payload.getProductId());
        if(existingStock.isPresent()){
            Stock stock = existingStock.get();
            stock.setQuantity(stock.getQuantity() + payload.getQuantity());
            stockRepository.save(stock);
        }else{
            Stock stockEnt  = stockMapper.toEntity(payload);
            stockRepository.save(stockEnt);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseModel("success", "Create stock successfully"));
    }


    public ResponseEntity<BaseResponseModel> updateStock(Long id, StockDto payload){
        Optional<Stock> fetchedStock = stockRepository.findById(id);
        if(fetchedStock.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseModel("failed", "Stock has not been found"));
        Stock existing = fetchedStock.get();
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setQuantity(payload.getQuantity());
        existing.setProductId(payload.getProductId());

        Stock updatedStock = stockRepository.save(existing);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<Stock>("success", "update stock successfully", updatedStock));
    }

    public ResponseEntity<BaseResponseModel> deleteStock(Long id){
        Optional<Stock> fetchedStock = stockRepository.findById(id);
        if(fetchedStock.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseModel("failed", "Stock has not been found"));

        stockRepository.deleteById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(new BaseResponseModel("success", "deleted stock successfully"));
    }
}
