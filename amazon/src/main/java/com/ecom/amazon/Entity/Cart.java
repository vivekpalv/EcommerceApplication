package com.ecom.amazon.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CartProduct> cartProducts = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Customer customer;

    //Helpers
    public void addProduct(CartProduct cartProduct){
        this.cartProducts.add(cartProduct);
        cartProduct.setCart(this); //Ensure Bidirectional relationship
    }

    public void removeProduct(CartProduct cartProduct){
        this.cartProducts.remove(cartProduct);
        cartProduct.setCart(null); // un-linking the relationship.
    }

    //Getters and Setters
    public long getId() {
        return id;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
