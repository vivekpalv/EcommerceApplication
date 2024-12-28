package com.ecom.amazon.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Customer customer;

    public void addProduct(Product product){
        this.products.add(product);
    }

    public long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
