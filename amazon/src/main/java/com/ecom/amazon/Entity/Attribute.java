package com.ecom.amazon.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String attributeKey;
    private String attributeValue;
    @Column(nullable = false)
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Product product;

    public long getId() {
        return id;
    }

    public String getKey() {
        return attributeKey;
    }

    public String getValue() {
        return attributeValue;
    }

    public double getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public void setKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    public void setValue(String value) {
        this.attributeKey = value;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
