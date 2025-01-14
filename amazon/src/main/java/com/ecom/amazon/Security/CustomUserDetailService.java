package com.ecom.amazon.Security;

import com.ecom.amazon.Entity.Customer;
import com.ecom.amazon.Entity.Vendor;
import com.ecom.amazon.Repository.CustomerRepository;
import com.ecom.amazon.Repository.VendorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public CustomUserDetailService(CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Vendor vendorByEmail = vendorRepository.findFirstByEmail(username);
//        Customer customerByEmail = customerRepository.findFirstByEmail(username);
//
//        if (vendorByEmail != null && customerByEmail != null) {
//            System.out.println("inside loadUserByUsername() of CustomUserDetailService class | vendor and customer both exist with same email: "+ username);
//        }
//
//
//        if (vendorByEmail != null) {
//            System.out.println("inside loadUserByUsername() of CustomUserDetailService class | vendor role: "+ vendorByEmail.getRole());
//            return new CustomUserDetails(vendorByEmail.getEmail(), vendorByEmail.getPassword(), vendorByEmail.getRole());
//        } else if (customerByEmail != null) {
//            System.out.println("inside loadUserByUsername() of CustomUserDetailService class | customer role: "+ customerByEmail.getRole());
//            return new CustomUserDetails(customerByEmail.getEmail(), customerByEmail.getPassword(), customerByEmail.getRole());
//        }

//        throw new UsernameNotFoundException("User not found with email: " + username);

//        System.out.println("inside loadUserByUsername() of CustomUserDetailService class | username: "+ username);
        return null;
    }

    public UserDetails loadUserByUsernameAndRole(String username, String role) throws UsernameNotFoundException {
        Vendor vendorByEmail = vendorRepository.findFirstByEmail(username);
        Customer customerByEmail = customerRepository.findFirstByEmail(username);

        if (vendorByEmail != null && customerByEmail != null) {
            System.out.println("inside loadUserByUsernameAndRole() of CustomUserDetailService class | vendor and customer both exist with same email: "+ username);
        }

        if (vendorByEmail != null && vendorByEmail.getRole().equals(role)) {
            System.out.println("inside loadUserByUsernameAndRole() of CustomUserDetailService class | vendor role: "+ vendorByEmail.getRole());
            return new CustomUserDetails(vendorByEmail.getEmail(), vendorByEmail.getPassword(), vendorByEmail.getRole());
        } else if (customerByEmail != null && customerByEmail.getRole().equals(role)) {
            System.out.println("inside loadUserByUsernameAndRole() of CustomUserDetailService class | customer role: "+ customerByEmail.getRole());
            return new CustomUserDetails(customerByEmail.getEmail(), customerByEmail.getPassword(), customerByEmail.getRole());
        }

        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}
