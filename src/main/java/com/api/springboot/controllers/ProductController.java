package com.api.springboot.controllers;

import com.api.springboot.dtos.ProductRecordDto;
import com.api.springboot.models.ProductModel;
import com.api.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable(value="id") UUID id){
        Optional<ProductModel> productModel = productRepository.findById(id);
        if (productModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productModel.get());
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Object> saveProduct(@PathVariable(value="id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto){
        Optional<ProductModel> productModel = productRepository.findById(id);
        if (productModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        ProductModel productModel1 = productModel.get();
        BeanUtils.copyProperties(productRecordDto, productModel1);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel1));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id){
        boolean productExists = productRepository.existsById(id);
        if (!productExists){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}
