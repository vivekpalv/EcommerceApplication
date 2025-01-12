package com.ecom.amazon.Service;

import com.ecom.amazon.Authentication.AuthDTO.LoginCustomerDTO;
import com.ecom.amazon.Authentication.AuthDTO.SignUpCustomerDTO;
import com.ecom.amazon.Entity.Customer;
import com.ecom.amazon.Repository.CustomerRepository;
import com.ecom.amazon.Security.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer createCustomer(SignUpCustomerDTO signUpCustomerDTO) {
        Customer customer = new Customer();
        customer.setEmail(signUpCustomerDTO.getEmail());
        customer.setPassword(passwordEncoder.encode(signUpCustomerDTO.getPassword()));
        return customerRepository.save(customer);
    }

    public boolean isUserExistByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    // customer login without AuthenticationManager
    public Customer customerLogin(LoginCustomerDTO loginCustomerDTO) {
        Customer customer = customerRepository.findFirstByEmail(loginCustomerDTO.getEmail());

        if (customer != null) {
            boolean isMatched = passwordEncoder.matches(loginCustomerDTO.getPassword(), customer.getPassword());
            if (isMatched) {
                return customer;
            }else {
                throw new RuntimeException("Password does not match");
            }
        }else {
            throw new RuntimeException("User does not exist");
        }
    }

    public Customer testCustomer(){
        Customer customer = customerRepository.findFirstByEmail("vivekpal14@gmail.com");
        System.out.println("test customer: "+customer);
        return customer;
    }

    public Customer getCustomerByEmail(String email){
        return customerRepository.findFirstByEmail(email);
    }

    public Customer currentLoggedInCustomer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication in currentLoggedInCustomer method: "+authentication);

        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            System.out.println("customUserDetails: "+customUserDetails.toString());

            String username = customUserDetails.getUsername();
            System.out.println("username: "+username);

            return customerRepository.findFirstByEmail(username);
        }else {
            throw new RuntimeException("User not logged in");
        }
    }
}
