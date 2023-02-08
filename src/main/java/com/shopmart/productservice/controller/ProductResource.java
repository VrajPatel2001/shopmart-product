package com.shopmart.productservice.controller;

import com.shopmart.productservice.model.Product;
import com.shopmart.productservice.source.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductResource {

    @Autowired
    private ProductRepository productRepository;

     @GetMapping("/products")
     public List<Product> retrieveAllProducts() {
         return productRepository.findAll();
     }


}
