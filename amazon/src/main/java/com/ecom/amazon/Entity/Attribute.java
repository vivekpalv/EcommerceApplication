package com.ecom.amazon.Entity;

import com.ecom.amazon.Entity.Embeded.AttributeField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ElementCollection
    private List<AttributeField> fields = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Product product;

    //Helper
    public void addField(String fieldKey, String fieldValue){
        AttributeField attributeField = new AttributeField(fieldKey, fieldValue);
        this.fields.add(attributeField);
    }

    public boolean removeField(String fieldKey){
//        this.fields.remove(attributeField);
        return this.fields.removeIf(f -> f.getFieldKey().equals(fieldKey));
    }

    public boolean updateField(String fieldKey, String updatingValue){
        for (AttributeField f : fields){
            if (f.getFieldValue().equals(fieldKey)){
                f.setFieldValue(updatingValue);
                return true;
            }
        }
        return false;
    }

    //Getters
    public long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public List<AttributeField> getFields() {
        return fields;
    }

    //Setters
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
