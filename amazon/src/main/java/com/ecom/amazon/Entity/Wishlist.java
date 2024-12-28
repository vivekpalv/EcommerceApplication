package com.ecom.amazon.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL) // don't use 'orphanRemoval = true' because it will delete the product from the database if it is removed product from the list.
    // It is a uni-directional relationship where product don't have any reference to wishlist, so not use jsonManagedReference + jsonBackReference.
    private List<Product> products = new ArrayList<>(); // don't use 'mappedBy' because it is not a bidirectional relationship.

    //Helpers
    public void addProduct(Product product){
        this.products.add(product);
    }

    public void removeProduct(Product product){
        this.products.remove(product);
    }

    //Getters
    public long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    //Setters
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
