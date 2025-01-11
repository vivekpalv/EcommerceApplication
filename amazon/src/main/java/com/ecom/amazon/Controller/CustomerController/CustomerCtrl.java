package com.ecom.amazon.Controller.CustomerController;

import com.ecom.amazon.DTO.Request.AddCustomerAddressDTO;
import com.ecom.amazon.Entity.Address;
import com.ecom.amazon.Entity.Customer;
import com.ecom.amazon.Service.AddressService;
import com.ecom.amazon.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerCtrl {

    private final CustomerService customerService;
    private final AddressService addressService;

    public CustomerCtrl(CustomerService customerService, AddressService addressService) {
        this.customerService = customerService;
        this.addressService = addressService;
    }

    @PostMapping("/addAddress")
    public ResponseEntity<?> addAddressToCustomer(@Valid @RequestBody AddCustomerAddressDTO addressDto, BindingResult result){

        if (result.hasErrors()) {
            HashMap<String, Object> errorHashMap = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> { errorHashMap.put(fieldError.getField(), fieldError.getDefaultMessage()); });
            return ResponseEntity.badRequest().body(errorHashMap);
        }

        System.out.println("AddCustomerAddressDTO data: "+addressDto);

        Customer customer = customerService.testCustomer();

        if (customer == null) {
            return ResponseEntity.badRequest().body("Customer not found");
        }

        List<Address> addresses = addressService.addAddressToCustomer(customer, addressDto);

        return ResponseEntity.ok(addresses);
    }
}
