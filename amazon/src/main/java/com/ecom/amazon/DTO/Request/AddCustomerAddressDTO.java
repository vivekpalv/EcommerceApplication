package com.ecom.amazon.DTO.Request;

import jakarta.validation.constraints.NotBlank;

public class AddCustomerAddressDTO {
    @NotBlank(message = "AddressText is required")
    private String addressText;
    private String city;
    private String state;
    @NotBlank(message = "Country is required")
    private String country;
    @NotBlank(message = "PinCode is required")
    private String pinCode;
    private String addressType;

    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @Override
    public String toString() {
        return "AddCustomerAddressDTO{" +
                "addressText='" + addressText + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", addressType='" + addressType + '\'' +
                '}';
    }
}
