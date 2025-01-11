package com.ecom.amazon.DTO.Request;

import com.ecom.amazon.Entity.Attribute;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class CreateProductDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Maximum Selling Price is required")
    private BigDecimal maximumSellingPrice;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price should be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity should be greater than 0")
    private int quantity;

    @NotNull(message = "Attributes are required")
    private List<AttributeDTO> attributes;

    private List<String> tags;

    //Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMaximumSellingPrice() {
        return maximumSellingPrice;
    }

    public void setMaximumSellingPrice(BigDecimal maximumSellingPrice) {
        this.maximumSellingPrice = maximumSellingPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<AttributeDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeDTO> attributes) {
        this.attributes = attributes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "CreateProductDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", maximumSellingPrice=" + maximumSellingPrice +
                ", price=" + price +
                ", quantity=" + quantity +
                ", attributes=" + attributes +
                ", tags=" + tags +
                '}';
    }
}
