package com.ecom.amazon.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;

    @Column(nullable = false)
    private BigDecimal finalAmount; // using double or Double can cause precision issue (we can't tolerate precision issue with currency)

    @Column(nullable = false)
    private BigDecimal offeredAmountWhileOrdering;

    private int orderedQuantity; // there is no precision issue with data type which store whole numbers

    @ManyToOne(cascade = CascadeType.ALL)
    private Attribute attribute;

    public long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public BigDecimal getOfferedAmountWhileOrdering() {
        return offeredAmountWhileOrdering;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public void setOfferedAmountWhileOrdering(BigDecimal offeredAmountWhileOrdering) {
        this.offeredAmountWhileOrdering = offeredAmountWhileOrdering;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
