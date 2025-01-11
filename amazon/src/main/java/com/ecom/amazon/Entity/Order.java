package com.ecom.amazon.Entity;

import com.ecom.amazon.Enum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // order is a reserved keyword in MySQL so we used 'orders' instead of 'order'
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String orderMessage;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderedProduct> orderedProducts = new ArrayList<>();

    @ManyToOne
    private Address address;

    @ManyToOne
    @JsonManagedReference
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //Helpers
    public void addOrderedProduct(OrderedProduct orderedProduct){
        this.orderedProducts.add(orderedProduct);
        orderedProduct.setOrder(this); // Ensure Bidirectional relationship
    }

    public long getId() {
        return id;
    }

    public String getOrderMessage() {
        return orderMessage;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public Address getAddress() {
        return address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
