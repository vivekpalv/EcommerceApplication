package com.ecom.amazon.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String addressText;
    private String city;
    private String state;
    private String country;
    private String pinCode;
    private String addressType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Customer customer;

    public long getId() {
        return id;
    }

    public String getAddressText() {
        return addressText;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getAddressType() {
        return addressType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", addressText='" + addressText + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", addressType='" + addressType + '\'' +
                ", customer=" + customer +
                '}';
    }
}
