package com.ecom.amazon.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Attribute> attributes = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Vendor vendor;

    //Helpers

    public void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
        attribute.setProduct(this);
    }

    //Getters

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public Vendor getVendor() {
        return vendor;
    }

    //Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
