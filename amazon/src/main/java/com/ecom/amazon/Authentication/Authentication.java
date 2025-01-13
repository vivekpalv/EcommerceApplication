package com.ecom.amazon.Authentication;

import com.ecom.amazon.Authentication.AuthDTO.LoginCustomerDTO;
import com.ecom.amazon.Authentication.AuthDTO.SignUpCustomerDTO;
import com.ecom.amazon.Authentication.AuthDTO.SignUpVendorDTO;
import com.ecom.amazon.Entity.Customer;
import com.ecom.amazon.Entity.Vendor;
import com.ecom.amazon.Jwt.JwtUtility;
import com.ecom.amazon.Service.CustomerService;
import com.ecom.amazon.Service.VendorService;
import com.ecom.amazon.Utility.ErrorUtility;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class Authentication {

    private final CustomerService customerService;
    private final VendorService vendorService;
    private final JwtUtility jwtUtility;
    private final AuthenticationManager authenticationManager;

    public Authentication(CustomerService customerService, VendorService vendorService, JwtUtility jwtUtility, AuthenticationManager authenticationManager) {
        this.customerService = customerService;
        this.vendorService = vendorService;
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Tested successfully");
    }

    @PostMapping("/customerSignUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpCustomerDTO signUpDto, BindingResult result) {

        if (result.hasErrors()) { return ErrorUtility.errorResponse(result); }

        System.out.println("dto data: "+signUpDto);

        boolean userExistByEmail = customerService.isUserExistByEmail(signUpDto.getEmail());
        if (userExistByEmail){
            return ResponseEntity.badRequest().body("User already exist");
        }

        Customer customer = customerService.createCustomer(signUpDto);
        System.out.println("entity data: "+customer);

        return ResponseEntity.ok(customer);
    }

    @PostMapping("/loginCustomer")
    public ResponseEntity<?> customerLogin(@Valid @RequestBody LoginCustomerDTO loginDto, BindingResult result){

        System.out.println("dto data: "+loginDto);

        if (result.hasErrors()) { return ErrorUtility.errorResponse(result); }

        try {
            Customer customer = customerService.customerLogin(loginDto);

            String token = jwtUtility.generateToken(customer.getEmail(), customer.getRole());

            return ResponseEntity.ok(token);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Customer login failed because of "+ e.getMessage());
        }
    }

    @PostMapping("/vendorSignUp")
    public ResponseEntity<?> vendorSignUp(@Valid @RequestBody SignUpVendorDTO vendorDto, BindingResult result) {

        if (result.hasErrors()){ return ErrorUtility.errorResponse(result); }

        System.out.println("SignUpVendorDTO: " + vendorDto);

        Vendor createdVendor = vendorService.createVendor(vendorDto);

        String token = jwtUtility.generateToken(createdVendor.getEmail(), createdVendor.getRole());

        return ResponseEntity.ok("vendor created successfully token: "+token);
    }

    @GetMapping("/loginVendor")
    public ResponseEntity<?> loginVendor(@RequestParam String email, @RequestParam String password) {

        if (email == null || password == null) {return ResponseEntity.badRequest().body("Email or password is missing");}

        try {
            Vendor vendor = vendorService.loginVendor(email, password);

            String token = jwtUtility.generateToken(vendor.getEmail(), vendor.getRole());

            return ResponseEntity.ok(token);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Vendor login failed because of "+ e.getMessage());
        }
    }
}
