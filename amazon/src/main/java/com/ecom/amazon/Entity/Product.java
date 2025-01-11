package com.ecom.amazon.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String description;

    @Column(nullable = false)
    private BigDecimal maximumSellingPrice;

    @Column(nullable = false)
    private BigDecimal price;

    private int quantity;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Attribute> attributes = new ArrayList<>();

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JsonBackReference
    private Vendor vendor;

    @ElementCollection
    @CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> tags = new ArrayList<>();

    //Helpers

    public void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
        attribute.setProduct(this); //Ensure Bidirectional relationship
    }

    public void removeAttribute(Attribute attribute){
        this.attributes.remove(attribute);
        attribute.setProduct(null); // un-linking the relationship.
    }

    public void addTag(String tag){
        this.tags.add(tag);
    }

    public void removeTag(String tag){
        this.tags.remove(tag);
    }

    public void removeVendor(Vendor vendor){
        this.vendor = null;
        vendor.getProducts().remove(this); //Ensure Bidirectional relationship
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

    public BigDecimal getMaximumSellingPrice() {
        return maximumSellingPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<String> getTags() {
        return tags;
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

    public void setMaximumSellingPrice(BigDecimal maximumSellingPrice) {
        this.maximumSellingPrice = maximumSellingPrice;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
