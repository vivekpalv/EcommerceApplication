package com.ecom.amazon.Controller.VendorController;

import com.ecom.amazon.DTO.Response.VendorOrderDTO;
import com.ecom.amazon.Entity.Address;
import com.ecom.amazon.Entity.Customer;
import com.ecom.amazon.Entity.OrderedProduct;
import com.ecom.amazon.Entity.Vendor;
import com.ecom.amazon.Service.AddressService;
import com.ecom.amazon.Service.OrderService;
import com.ecom.amazon.Service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vendor/order")
public class OrderVendorController {

    private final OrderService orderService;
    private final VendorService vendorService;
    private final AddressService addressService;

    public OrderVendorController(OrderService orderService, VendorService vendorService, AddressService addressService) {
        this.orderService = orderService;
        this.vendorService = vendorService;
        this.addressService = addressService;
    }

    @GetMapping("/getOrders")
    public ResponseEntity<?> vendorOrders(){
        Vendor currentVendor = vendorService.currentLoggedInVendor();
        List<OrderedProduct> allVendorOrderProducts = orderService.getAllVendorOrderProducts(currentVendor);
        Address address = addressService.getAddress(allVendorOrderProducts.getFirst());
        Customer customer = address.getCustomer();

        VendorOrderDTO responseBody = new VendorOrderDTO(address, customer, allVendorOrderProducts);
        return ResponseEntity.ok(responseBody);
    }
}
