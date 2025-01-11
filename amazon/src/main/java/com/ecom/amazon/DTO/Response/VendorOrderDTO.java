package com.ecom.amazon.DTO.Response;

import com.ecom.amazon.Entity.Address;
import com.ecom.amazon.Entity.Customer;
import com.ecom.amazon.Entity.OrderedProduct;

import java.util.List;

public class VendorOrderDTO {

    private Address address;
    private List<OrderedProduct> orderedProducts;
    private Customer customer;


    public VendorOrderDTO(Address address, Customer customer, List<OrderedProduct> orderedProducts) {
        this.address = address;
        this.customer = customer;
        this.orderedProducts = orderedProducts;
    }

    public Address getAddress() {
        return address;
    }

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "VendorOrderDTO{" +
                "address=" + address +
                ", orderedProducts=" + orderedProducts +
                '}';
    }
}
