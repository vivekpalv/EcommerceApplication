package com.ecom.amazon.Service;

import com.ecom.amazon.DTO.Request.AddCustomerAddressDTO;
import com.ecom.amazon.Entity.Address;
import com.ecom.amazon.Entity.Customer;
import com.ecom.amazon.Entity.OrderedProduct;
import com.ecom.amazon.Repository.AddressRepository;
import com.ecom.amazon.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    public List<Address> addAddressToCustomer(Customer customer, AddCustomerAddressDTO addressDto) {
        Address address = new Address();
        address.setCustomer(customer);
        address.setAddressText(addressDto.getAddressText());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setPinCode(addressDto.getPinCode());
        address.setState(addressDto.getState());
        address.setAddressType(addressDto.getAddressType());

        customer.addAddress(address);
//        System.out.println("address service customer: "+customer); // causing stackoverflow error, because of circular reference of customer and address (inside of toString(), while printing addresses list)
        Customer customerWithSaveAddress = customerRepository.save(customer);// we are using 'cascadeType.All' so, if we save customer after using addAddress() of customer, then address details will auto save in 'address table' also, because of cascadeType.ALL

//        Address savedAddress = addressRepository.save(address); // it will save address details 'again' in address table (so, in short if we use 'customerRepository.save(customer)' and 'addressRepository.save(address)' both then, address details will save twice in 'address table')

        return customerWithSaveAddress.getAddresses();
    }

    public Address getAddress(OrderedProduct orderedProduct) {
        Address address = orderedProduct.getOrder().getAddress();
        return addressRepository.findById(address.getId()).orElseThrow(() -> new RuntimeException("Address not found"));
    }
}