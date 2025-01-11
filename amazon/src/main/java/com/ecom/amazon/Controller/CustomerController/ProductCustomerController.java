package com.ecom.amazon.Controller.CustomerController;

import com.ecom.amazon.Entity.*;
import com.ecom.amazon.Service.AttributeService;
import com.ecom.amazon.Service.CartService;
import com.ecom.amazon.Service.CustomerService;
import com.ecom.amazon.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/product")
public class ProductCustomerController {

    private final ProductService productService;
    private final CustomerService customerService;
    private final CartService cartService;
    private final AttributeService attributeService;

    public ProductCustomerController(ProductService productService, CustomerService customerService, CartService cartService, AttributeService attributeService) {
        this.productService = productService;
        this.customerService = customerService;
        this.cartService = cartService;
        this.attributeService = attributeService;
    }


    @GetMapping("/getProducts")
    public ResponseEntity<?> getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String searchString) {

        List<Product> products = productService.searchedProducts(searchString, page, size);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/addProductToCart")
    public ResponseEntity<?> addProductToCart(@RequestParam Long productId, @RequestParam Long attributeId, @RequestParam int quantity) {

        if (productId == null || attributeId == null || quantity <= 0) { return ResponseEntity.badRequest().body("Invalid request"); }

        Customer currentCustomer = customerService.testCustomer();
        Product product = productService.getProduct(productId, attributeId);
        Attribute attribute = attributeService.getAttribute(attributeId);
        Cart cart = cartService.addProductToCart(currentCustomer, product, attribute, quantity);

        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/removeProductFromCart")
    public ResponseEntity<?> removeProductFromCart(@RequestParam Long cartProductId) {

        if (cartProductId == null) { return ResponseEntity.badRequest().body("Invalid request"); }

        Customer currentCustomer = customerService.testCustomer();
        CartProduct cartProduct = cartService.getCartProduct(cartProductId);
        Cart cart = cartService.removeProductFromCart(currentCustomer, cartProduct);

        return ResponseEntity.ok(cart);
    }
}
