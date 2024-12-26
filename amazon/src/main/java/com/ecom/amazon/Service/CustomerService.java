package com.ecom.amazon.Service;

import com.ecom.amazon.Authentication.AuthDTO.LoginCustomerDTO;
import com.ecom.amazon.Authentication.AuthDTO.SignUpCustomerDTO;
import com.ecom.amazon.Entity.Customer;
import com.ecom.amazon.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(SignUpCustomerDTO signUpCustomerDTO) {
        Customer customer = new Customer();
        customer.setEmail(signUpCustomerDTO.getEmail());
        customer.setPassword(signUpCustomerDTO.getPassword());
        Customer createdCustomer = customerRepository.save(customer);
        return createdCustomer;
    }

    public boolean isUserExistByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    public Customer customerLogin(LoginCustomerDTO loginCustomerDTO) {
        Customer customer = customerRepository.findFirstByEmail(loginCustomerDTO.getEmail());

        System.out.println("service login customer: "+customer);

        if (customer != null && customer.getPassword().equals(loginCustomerDTO.getPassword())) {
            return customer;
        }

        return null;
    }

    public Customer testCustomer(){
        Customer customer = customerRepository.findFirstByEmail("vivekpal14@gmail.com");
        System.out.println("test customer: "+customer);
        return customer;
    }
}
