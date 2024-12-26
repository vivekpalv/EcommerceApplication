package com.ecom.amazon.Authentication;

import com.ecom.amazon.Authentication.AuthDTO.LoginCustomerDTO;
import com.ecom.amazon.Authentication.AuthDTO.SignUpCustomerDTO;
import com.ecom.amazon.Entity.Customer;
import com.ecom.amazon.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class Authentication {

    private final CustomerService customerService;

    public Authentication(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Tested successfully");
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpCustomerDTO signUpDto, BindingResult result) {
        if (result.hasErrors()) {
//            return ResponseEntity.badRequest().body(result.getAllErrors());
            HashMap<String, Object> errorHashMap = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> { errorHashMap.put(fieldError.getField(), fieldError.getDefaultMessage()); });
            return ResponseEntity.badRequest().body(errorHashMap);
        }

        boolean userExistByEmail = customerService.isUserExistByEmail(signUpDto.getEmail());
        if (userExistByEmail){
            return ResponseEntity.badRequest().body("User already exist");
        }

        System.out.println("dto data: "+signUpDto);
        Customer customer = customerService.createCustomer(signUpDto);
        System.out.println("entity data: "+customer);

        return ResponseEntity.ok(customer);
    }

    @PostMapping("/loginCustomer")
    public ResponseEntity<?> customerLogin(@Valid @RequestBody LoginCustomerDTO loginDto, BindingResult result){

        if (result.hasErrors()) {
            HashMap<String, Object> errorHashMap = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> { errorHashMap.put(fieldError.getField(), fieldError.getDefaultMessage()); });
            return ResponseEntity.badRequest().body(errorHashMap);
        }

        System.out.println("dto data: "+loginDto);

        Customer customer = customerService.customerLogin(loginDto);

        System.out.println("entity data: "+customer);

        if (customer == null){
            return ResponseEntity.badRequest().body("Login failed");
        }

        return ResponseEntity.ok(customer);
    }
}
