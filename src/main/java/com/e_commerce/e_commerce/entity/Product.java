package com.e_commerce.e_commerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    private String product_id;

    private String product_name;
    private String category;
    private String brand;
    private String price;
    private String added_date;

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // TODO: price should ideally be a numeric type (e.g., double or BigDecimal)

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    // TODO: added_date can be parsed into Date or LocalDateTime for better handling

    public String getAddedDate() {
        return added_date;
    }

    public void setAddedDate(String added_date) {
        this.added_date = added_date;
    }
}
