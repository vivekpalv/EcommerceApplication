package com.ecom.amazon.Controller.VendorController;

import com.ecom.amazon.DTO.Request.CreateProductDTO;
import com.ecom.amazon.Entity.Product;
import com.ecom.amazon.Entity.Vendor;
import com.ecom.amazon.Service.ProductService;
import com.ecom.amazon.Service.VendorService;
import com.ecom.amazon.Utility.ErrorUtility;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/vendor/product")
public class ProductVendorController {

    private final ProductService productService;
    private final VendorService vendorService;

    public ProductVendorController(ProductService productService, VendorService vendorService) {
        this.productService = productService;
        this.vendorService = vendorService;
    }

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductDTO productDTO, BindingResult result){
        if (result.hasErrors()){ return ErrorUtility.errorResponse(result); }

        System.out.println("product dto: " + productDTO.toString());

        Vendor currentVendor = vendorService.currentLoggedInVendor();
        Product product = productService.createProduct(productDTO, currentVendor);

        System.out.println("created product: " + product.toString());
        Vendor currentVendor1 = vendorService.currentLoggedInVendor();

        return ResponseEntity.ok(currentVendor1);
    }
}
