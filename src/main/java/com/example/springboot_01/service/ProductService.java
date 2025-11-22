package com.example.springboot_01.service;

import com.example.springboot_01.entity.Product;
import com.example.springboot_01.model.ApiResponseModel;
import com.example.springboot_01.model.BaseResponseModel;
import com.example.springboot_01.model.ProductModel;
import com.example.springboot_01.repository.ProductRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<BaseResponseModel> listProduct () {
       List<Product> products = productRepository.findAll();
       return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<List<Product>>("success", "get list of products successfully", products));
    }

    public ResponseEntity<BaseResponseModel> getOneProduct(@PathVariable("id") Long id){
        Optional<Product> fetchedProduct = productRepository.findById(id);
        if(fetchedProduct.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseModel("failed", "product with id = " + id + " is not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<Optional<Product>>("success", "Get one product successfully", fetchedProduct));
    }
    public ResponseEntity<BaseResponseModel> createProduct(@RequestBody ProductModel payload){
        Product newProduct = new Product();
        newProduct.setProductName(payload.getProductName());
        newProduct.setPrice(payload.getPrice());
        newProduct.setDescription(payload.getDescription());
        newProduct.setCreatedAt(LocalDateTime.now());
        newProduct.setUpdatedAt(LocalDateTime.now());
        productRepository.save(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseModel("success", "created product successfully"));
    }


    public ResponseEntity<BaseResponseModel> updateProduct (@PathVariable("id") Long id, @RequestBody ProductModel payload){
        Optional<Product> fetchedProduct = productRepository.findById(id);
        if(fetchedProduct.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseModel("failed", "product with id = " + id + " is not found"));
        }
        Product existing = fetchedProduct.get();
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setProductName(payload.getProductName());
        existing.setPrice(payload.getPrice());
        existing.setDescription(payload.getDescription());

        Product updatedProduct =  productRepository.save(existing);
        return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<Product>("success", "updated product successfully", updatedProduct));

    }
    public ResponseEntity<BaseResponseModel> deleteProduct(Long id){
        Optional<Product> fetchedProduct = productRepository.findById(id);
        if(fetchedProduct.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseModel("failed", "product with id = " + id + " is not found"));
        }
        productRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new BaseResponseModel("failed", "product deleted successfully"));
    }


    public ResponseEntity<BaseResponseModel> filterProduct(String query, Double minPrice, Double maxPrice){
       List<Product> fetchedProduct = productRepository.filterProduct(query, minPrice, maxPrice);
       return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<List<Product>>("success", "successfully get products", fetchedProduct));
    }
}
