package com.ecom.amazon.Service;

import com.ecom.amazon.Authentication.AuthDTO.SignUpVendorDTO;
import com.ecom.amazon.CustomException.VendorException;
import com.ecom.amazon.Entity.Vendor;
import com.ecom.amazon.Repository.VendorRepository;
import com.ecom.amazon.Security.CustomUserDetails;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;

    public VendorService(VendorRepository vendorRepository, PasswordEncoder passwordEncoder) {
        this.vendorRepository = vendorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Vendor createVendor(SignUpVendorDTO vendorDto) {
        try {
            Vendor vendor = new Vendor(vendorDto.getName(), vendorDto.getEmail(), vendorDto.getMobile(), passwordEncoder.encode(vendorDto.getPassword()));
            return this.vendorRepository.save(vendor);
        }catch (DataAccessException error){
            throw new VendorException("Vendor creation failed (may be application loose connectivity to database)", error);
        }catch (Exception error){
            throw new VendorException("An un-expected error occurred inside createVendor method", error);
        }
    }

    public Vendor currentLoggedInVendor(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication: "+authentication);

        if (authentication != null){
            CustomUserDetails customDetails = (CustomUserDetails) authentication.getPrincipal();
            System.out.println("customDetails: "+customDetails.toString());

            String username = customDetails.getUsername();
            System.out.println("username: "+username);

            return vendorRepository.findFirstByEmail(username);
        }else {
            throw new VendorException("Vendor not logged in");
        }
    }

    public Vendor getVendorByEmail(String email){
        return vendorRepository.findFirstByEmail(email);
    }

    public Vendor loginVendor(String email, String password) {
        Vendor vendor = vendorRepository.findFirstByEmail(email);
        if (vendor != null) {
            boolean isMatched = passwordEncoder.matches(password, vendor.getPassword());
            if (isMatched) {
                return vendor;
            }else {
                throw new RuntimeException("Password does not match");
            }
        }else {
            throw new RuntimeException("User does not exist");
        }
    }
}
