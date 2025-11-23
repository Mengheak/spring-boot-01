package com.example.springboot_01.controller;


import com.example.springboot_01.model.BaseResponseModel;
import com.example.springboot_01.dto.product.ProductDto;
import com.example.springboot_01.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<BaseResponseModel> listProduct() {
        return productService.listProduct();
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseModel> getOneProduct(@PathVariable("id") Long id){
        return productService.getOneProduct(id);
    }
    @PostMapping
    public ResponseEntity<BaseResponseModel> createProduct(@RequestBody ProductDto payload){
        return productService.createProduct(payload);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseModel> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto payload){
        return productService.updateProduct(id, payload);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseModel> deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponseModel> filterProduct(
            @RequestParam(name = "query", required = false)  String query,
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice
    ){
        return productService.filterProduct(query, minPrice, maxPrice);
    }
}
