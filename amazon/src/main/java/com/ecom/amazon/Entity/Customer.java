package com.ecom.amazon.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String email;
    private String mobile;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Address> addresses = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Wallet wallet;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Cart cart;

    @PrePersist
    public void prePersist() {
        Wallet wallet = new Wallet();
        wallet.setBalance(0);
        wallet.setCustomer(this);
        this.wallet = wallet;

        Cart cart = new Cart();
        cart.setCustomer(this);
        this.cart = cart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Cart getCart() {
        return cart;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
        address.setCustomer(this);
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
