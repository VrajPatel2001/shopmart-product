package com.shopmart.productservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopmart.productservice.model.Product;
import com.shopmart.productservice.source.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ProductResource {

    @Autowired
    private ProductRepository productRepository;

     @GetMapping("/products")
     public List<Product> retrieveAllProducts() {
         return productRepository.findAll();
     }

     @PostMapping(value = "/product",consumes = "multipart/form-data")
    public Product createProduct(@RequestParam("encodedImage") String encodedImage, @RequestParam("product") String productData) throws JsonProcessingException {
         ObjectMapper mapper = new ObjectMapper();
         Product product = mapper.readValue(productData, Product.class);


         return productRepository.save(product);
    }


}
