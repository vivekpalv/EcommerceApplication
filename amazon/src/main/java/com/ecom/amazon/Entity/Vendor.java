package com.ecom.amazon.Entity;

import com.ecom.amazon.Enum.ROLE;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String email;
    @Column(nullable = false, unique = true) //nullable should place first.
    private long mobile;
    private String password;
    private String gst;

    @Enumerated(EnumType.STRING)
    private ROLE role = ROLE.VENDOR;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        this.products.add(product);
        product.setVendor(this);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getGst() {
        return gst;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }
}
