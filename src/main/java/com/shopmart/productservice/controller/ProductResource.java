package com.shopmart.productservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.Credentials;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.StorageClient;
import com.shopmart.productservice.model.Product;
import com.shopmart.productservice.source.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.*;

import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class ProductResource {

    @Autowired
    private ProductRepository productRepository;

    @Value("${FIREBASE_APPLICATION_CREDENTIAL}")
    String path;

    @GetMapping("/products")
     public List<Product> retrieveAllProducts() {
         return productRepository.findAll();
     }

     @PostMapping(value = "/product",consumes = "multipart/form-data")
    public Product createProduct(@RequestParam("encodedImage") String encodedImage, @RequestParam("product") String productData) throws IOException {

         ObjectMapper mapper = new ObjectMapper();
         Product product = mapper.readValue(productData, Product.class);

         byte[] decodedBytes = Base64.getDecoder().decode(encodedImage);
         InputStream inputStream = new ByteArrayInputStream(decodedBytes);

         Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(path));
         Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

         BlobId blobId = BlobId.of("e-commerce-73720.appspot.com", UUID.randomUUID().toString());
         BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();

         Blob blob = storage.create(blobInfo, inputStream.readAllBytes());
         String imageUrl = blob.getMediaLink();

            product.setImageURL(imageUrl);
            System.out.println("Product: " + product);


         return productRepository.save(product);
    }


}
