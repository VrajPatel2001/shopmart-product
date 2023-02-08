package com.shopmart.productservice.model;


import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String description;
        private Double price;
        private String imageURL;

        public Product() {
        }

        public Product(Long id, String name, String description, Double price) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
