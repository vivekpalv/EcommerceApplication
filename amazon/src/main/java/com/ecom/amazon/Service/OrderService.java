package com.ecom.amazon.Service;

import com.ecom.amazon.Entity.*;
import com.ecom.amazon.Repository.OrderRepository;
import com.ecom.amazon.Repository.OrderedProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, OrderedProductRepository orderedProductRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.cartService = cartService;
    }

    public Order createOderWithCart(Customer customer, Address address){
//        List<CartProduct> cartProducts = customer.getCart().getCartProducts();
        List<CartProduct> cartProducts = cartService.getCartProducts(customer);


        Order order = new Order();
        order.setCustomer(customer);
        order.setAddress(address);

        for (CartProduct cartProduct: cartProducts){
            OrderedProduct orderedProduct = new OrderedProduct();
            orderedProduct.setProduct(cartProduct.getProduct());

            orderedProduct.setAttribute(cartProduct.getSelectedAttribute());
            orderedProduct.setFinalAmount(cartProduct.getSelectedAttribute().getPrice());
            orderedProduct.setOfferedAmountWhileOrdering(cartProduct.getProduct().getPrice());
            orderedProduct.setVendor(cartProduct.getProduct().getVendor());
            order.addOrderedProduct(orderedProduct);
        }

        order.setTotalAmount(BigDecimal.valueOf(1000.0)); // static value for now
        order.setOrderMessage("static message for now");

        return this.orderRepository.save(order);
    }

    public List<OrderedProduct> getAllVendorOrderProducts(Vendor vendor){
        List<OrderedProduct> orderedProducts = orderedProductRepository.findByVendorId(vendor.getId());
        return orderedProducts;
    }
}
