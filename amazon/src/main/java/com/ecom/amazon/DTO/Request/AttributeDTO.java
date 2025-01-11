package com.ecom.amazon.DTO.Request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class AttributeDTO {

    @NotNull(message = "attribute dto price can't be null")
    private BigDecimal price;

    private List<AttributeFieldDTO> fields;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<AttributeFieldDTO> getFields() {
        return fields;
    }

    public void setFields(List<AttributeFieldDTO> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "AttributeDTO{" +
                "price=" + price +
                ", fields=" + fields +
                '}';
    }
}
