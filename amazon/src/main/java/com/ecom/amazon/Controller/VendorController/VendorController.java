package com.ecom.amazon.Controller.VendorController;

import com.ecom.amazon.Entity.Vendor;
import com.ecom.amazon.Service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/testVendor")
    public ResponseEntity<?> getVendor(){
        Vendor currentVendor = vendorService.currentLoggedInVendor();
        return ResponseEntity.ok(currentVendor);
    }
}
