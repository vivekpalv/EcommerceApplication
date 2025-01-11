package com.ecom.amazon.Controller.CustomerController;

import com.ecom.amazon.Entity.Address;
import com.ecom.amazon.Entity.Customer;
import com.ecom.amazon.Entity.Order;
import com.ecom.amazon.Service.CartService;
import com.ecom.amazon.Service.CustomerService;
import com.ecom.amazon.Service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/order")
public class OrderCustomerController {

    private final OrderService orderService;
    private final CartService cartService;
    private final CustomerService customerService;

    public OrderCustomerController(OrderService orderService, CartService cartService, CustomerService customerService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.customerService = customerService;
    }

        @PostMapping("/createOrderWithCart")
    public ResponseEntity<?> createOrderWithCart(){
        Customer currentCustomer = customerService.testCustomer();
            Address address = customerService.testCustomer().getAddresses().get(0);
            Order createdOrder = orderService.createOderWithCart(currentCustomer, address);
        return ResponseEntity.ok(createdOrder);
    }
}
